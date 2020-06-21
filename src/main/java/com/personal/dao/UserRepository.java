package com.personal.dao;

import com.personal.models.UserCredential;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.RxJavaCrudRepository;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import java.util.UUID;

@Repository
public interface UserRepository extends RxJavaCrudRepository<UserCredential, UUID> {

    Maybe<UserCredential> findByUsername(String username);
    Flowable<UserCredential> findByUsernameLike(String usernameQuery);
}
