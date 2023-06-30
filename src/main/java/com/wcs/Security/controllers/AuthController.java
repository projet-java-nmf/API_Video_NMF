package com.wcs.Security.controllers;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.User;
import com.wcs.Security.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-up-user")
    public ResponseEntity<String> createUser (@RequestBody User user){
        User result = userService.createUser(user);

        try {
            userService.addRoleToUser(result.getEmail(), RoleName.USER);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sign-up-admin")
    public ResponseEntity<String> createAdmin (@RequestBody User user){
        User result = userService.createUser(user);

        try {
            userService.addRoleToUser(result.getEmail(), RoleName.ADMIN);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("/login")
    public ResponseEntity<?> login (@RequestBody Map<String, String> request){
     try {
         userService.login(request.get("email"), request.get("password"));
         return new ResponseEntity<>("Success", HttpStatus.OK);
     }catch (Exception e){
         return new ResponseEntity<>("Erreur", HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }
}
