package com.example.eshop.controller;

import com.example.eshop.data.entity.User;
import com.example.eshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable Long id) {
        try{
            return userService.getUser(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There's no user with this id");
        }
    }

    @PostMapping("/create-user")
    public String createUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return "User " + user.getName() + " saved!";
    }

    @PutMapping("/edit-user")
    public String editUser(@Valid @RequestBody User user) {
        try{
            userService.saveUser(user);
            return "User data changed";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There's no user with this id");
        }
    }
}
