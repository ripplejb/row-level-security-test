package com.personal.services.user;

import com.personal.dao.UserRepository;
import com.personal.models.dtos.UserDto;
import com.personal.models.search.requests.UserSearchRequest;
import com.personal.services.mappers.UserMapper;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserServiceImpl implements UserService{

    @Inject
    private UserRepository userRepository;

    @Override
    public Single<UserDto> createUser(UserDto userDto) {
        return userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDto))
                .map(UserMapper.INSTANCE::userToUserDto);
    }

    @Override
    public Single<UserDto> updateUser(UserDto userDto) {
        return userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDto))
                .map(UserMapper.INSTANCE::userToUserDto);
    }

    @Override
    public Flowable<UserDto> search(UserSearchRequest userSearchRequest) {
        return userRepository.findByUsernameLike("%"+ userSearchRequest.getNameContains() + "%")
                .map(UserMapper.INSTANCE::userToUserDto);
    }
}
