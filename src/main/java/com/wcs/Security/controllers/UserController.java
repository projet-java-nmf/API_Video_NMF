package com.wcs.Security.controllers;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.User;
import com.wcs.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getAllUsers (){
        return userService.getAllUser();
    }

    @PostMapping("/sign-up-user")
    public void createUser (@RequestBody User user){
        User result = userService.createUser(user);
        userService.addRoleToUser(result.getEmail(), RoleName.USER);
    }

    @PostMapping("/sign-up-admin")
    public void createAdmin (@RequestBody User user){
        User result = userService.createUser(user);
        userService.addRoleToUser(result.getEmail(), RoleName.ADMIN);
    }

    @PostMapping ("/login")
    public String login (@RequestBody Map<String, String> request){
        return userService.login(request.get("email"), request.get("password"));
    }
}
