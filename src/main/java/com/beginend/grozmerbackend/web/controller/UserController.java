package com.beginend.grozmerbackend.web.controller;

import com.beginend.grozmerbackend.model.User;
import com.beginend.grozmerbackend.service.storage.StorageService;
import com.beginend.grozmerbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "all")
    public List<User> findAll(@RequestParam(required = false) String search){
        return this.userService.findAll(search);
    }

    @PostMapping("save")
    public User save(@RequestBody User user){
        return this.userService.save(user);
    }
}
