package com.anisimovdenis.services;

import com.anisimovdenis.exceptions.ResourceNotFoundException;
import com.anisimovdenis.entities.User;
import com.anisimovdenis.entities.UserDto;
import com.anisimovdenis.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findUserById(Long id) {
        return userRepository.findById(id).map(UserDto::new).orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User saveNewUser(User newUser) {
        return userRepository.save(newUser);
    }
}
