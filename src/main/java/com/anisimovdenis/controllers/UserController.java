package com.anisimovdenis.controllers;

import com.anisimovdenis.entities.User;
import com.anisimovdenis.entities.UserDto;
import com.anisimovdenis.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        log.info("Request to get user with id: {}", id);
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUserAllUsers() {
        log.info("Request to get list of users");
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> saveNewUser(@RequestBody User newUser) {
        log.info("Request to save new user: {}", newUser);
        User user = userService.saveNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UserDto(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        log.info("Request to delete user with id: {}", id);
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
