package com.personal.services.security.providers;

import com.personal.dao.UserRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private UserRepository userRepository;

    /**
     * Authenticates a user with the given request. If a successful authentication is
     * returned, the object must be an instance of {@link UserDetails}.
     *
     * @param authenticationRequest The request to authenticate
     * @return A publisher that emits 0 or 1 responses
     * @deprecated Use {@link #authenticate(HttpRequest, AuthenticationRequest)} instead.
     */
    @Override
    @Deprecated
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        return null;
    }

    /**
     * Authenticates a user with the given request. If a successful authentication is
     * returned, the object must be an instance of {@link UserDetails}.
     *
     * @param request               The HTTP request
     * @param authenticationRequest The request to authenticate
     * @return A publisher that emits 0 or 1 responses
     */
    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> request,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        return userRepository.findByUsername(authenticationRequest.getIdentity().toString())
                .map(user -> {
                    if (user.getPassword().equals(authenticationRequest.getSecret())) {
                        if (user.getUsername().endsWith("Admin")) {
                            return new UserDetails(user.getId().toString(),
                                    List.of("user.full", "order.full"));
                        } else {
                            return new UserDetails(user.getId().toString(),
                                    List.of("self:user.full", "self:order.full"));
                        }
                    } else {
                        return new AuthenticationFailed("Invalid Secret.");
                    }
                })
                .defaultIfEmpty(new AuthenticationFailed("UserCredential Not Found."))
                .toFlowable();
    }
}
