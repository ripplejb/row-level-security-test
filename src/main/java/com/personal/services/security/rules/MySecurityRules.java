package com.personal.services.security.rules;

import com.personal.models.configuration.AuthorizationPolicy;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.rules.ConfigurationInterceptUrlMapRule;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.rules.SecurityRuleResult;
import io.micronaut.web.router.MethodBasedRouteMatch;
import io.micronaut.web.router.RouteMatch;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class MySecurityRules implements SecurityRule {

    @Inject
    List<AuthorizationPolicy> policies;

    /**
     * The order of the rule.
     */
    public static final Integer ORDER = ConfigurationInterceptUrlMapRule.ORDER - 100;

    /**
     * Returns a security result based on any conditions.
     *
     * @param request    The current request
     * @param routeMatch The matched route or empty if no route was matched. e.g. static resource.
     * @param claims     The claims from the token. Null if not authenticated
     * @return The result
     * @see SecurityRuleResult
     */
    @Override
    public SecurityRuleResult check(HttpRequest request,
                                    @Nullable RouteMatch routeMatch,
                                    @Nullable Map<String, Object> claims) {
        if (routeMatch instanceof MethodBasedRouteMatch) {
            MethodBasedRouteMatch methodRoute = ((MethodBasedRouteMatch) routeMatch);
            if (methodRoute.hasAnnotation(SecurityPolicy.class)) {
                Optional<String> optionalValue = methodRoute.getValue(SecurityPolicy.class, String.class);
                if (optionalValue.isPresent()) {
                    String policyName = optionalValue.get();
                    if (policyName.contains(SecurityRule.DENY_ALL)) {
                        return SecurityRuleResult.REJECTED;
                    }
                    if (policyName.contains(SecurityRule.IS_ANONYMOUS)) {
                        return SecurityRuleResult.ALLOWED;
                    }
                    if (claims == null)
                        return SecurityRuleResult.REJECTED;
                    return checkAuthorizationPolicies(claims, policyName);
                }
            }
        }
        return SecurityRuleResult.UNKNOWN;
    }

    private SecurityRuleResult checkAuthorizationPolicies(Map<String, Object> claims,
                                                          String policyName) {
        if (claims.containsKey("scopes")) {
            List<String> scopes;
            if (claims.get("scopes") instanceof ArrayList) {
                scopes = (ArrayList<String>) claims.get("scopes");
            } else {
                return SecurityRuleResult.REJECTED;
            }

            if (scopes.isEmpty())
                return SecurityRuleResult.REJECTED;

            List<String> scopesConfig = policies
                    .stream()
                    .filter(p -> p.getName().equals(policyName))
                    .findFirst()
                    .map(AuthorizationPolicy::getScopes)
                    .orElse(new ArrayList<>());

            List<String> validScopes = scopesConfig
                    .stream()
                    .filter(
                            scope -> scopes.stream()
                                    .anyMatch(s -> s.equals(scope)))
                    .collect(Collectors.toList());

            if (validScopes.isEmpty())
                return SecurityRuleResult.REJECTED;
            return SecurityRuleResult.ALLOWED;
        }

        return SecurityRuleResult.REJECTED;
    }

    /**
     * @return The order of the object. Defaults to zero (no order).
     */
    @Override
    public int getOrder() {
        return ORDER;
    }
}
