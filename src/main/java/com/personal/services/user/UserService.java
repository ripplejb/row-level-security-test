package com.personal.services.user;

import com.personal.models.dtos.UserDto;
import com.personal.models.search.requests.UserSearchRequest;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface UserService {
    Single<UserDto> createUser(UserDto userDto);
    Single<UserDto> updateUser(UserDto userDto);
    Flowable<UserDto> search(UserSearchRequest userSearchRequest);
}
