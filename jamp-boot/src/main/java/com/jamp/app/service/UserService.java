package com.jamp.app.service;

import java.util.Collection;
import java.util.Optional;

import com.jamp.app.domain.User;
import com.jamp.app.domain.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}
