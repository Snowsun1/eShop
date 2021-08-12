package com.example.eshop.controller;

import com.example.eshop.model.User;
import com.example.eshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/user")
    public String createUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return "User " + user.getName() + " saved!";
    }

    @PostMapping("/edit-user/{id}")
    public String editUser(@Valid @RequestBody User user, @PathVariable Long id) {
        userService.editUser(user, id);
        return "User data changed";
    }
}
