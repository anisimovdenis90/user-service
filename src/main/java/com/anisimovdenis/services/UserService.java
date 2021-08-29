package com.anisimovdenis.services;

import com.anisimovdenis.entities.User;
import com.anisimovdenis.entities.UserDto;

import java.util.List;

public interface UserService {

    UserDto findUserById(Long id);

    List<UserDto> findAllUsers();

    void deleteUserById(Long ig);

    User saveNewUser(User newUser);

}
