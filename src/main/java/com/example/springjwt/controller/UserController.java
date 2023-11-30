package com.example.springjwt.controller;

import com.example.springjwt.entity.User;
import com.example.springjwt.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("{register-new-user")
    public User registerNewUser(@RequestBody User user)
    {
        return userService.registerNewUser(user);
    }

    @PostConstruct
    public void initRoleAndUser()
    {
        userService.initRoleAndUser();
    }



}
