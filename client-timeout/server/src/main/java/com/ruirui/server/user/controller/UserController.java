package com.ruirui.server.user.controller;

import com.ruirui.server.user.dto.UserDTO;
import com.ruirui.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users", consumes = "application/json")
    public boolean createUser(@RequestBody UserDTO user) throws InterruptedException {
        userService.createUser(user);
        System.out.println("Controller return");
        return true;
    }
}
