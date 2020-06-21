package com.personal.controllers;

import com.personal.models.dtos.UserDto;
import com.personal.services.security.rules.SecurityPolicy;
import com.personal.services.user.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;

import javax.inject.Inject;

@Controller("/users")
public class UserController {

    @Inject
    private UserService userService;

    @SecurityPolicy(SecurityRule.IS_ANONYMOUS)
    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    Single<HttpResponse<UserDto>> Post(UserDto userDto) {
        return userService.createUser(userDto)
                .map(user -> HttpResponse.ok(user));
    }

}
