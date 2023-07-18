package com.wcs.Security.controllers;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.exceptions.UserException;
import com.wcs.Security.models.Role;
import com.wcs.Security.models.User;
import com.wcs.Security.models.Video;
import com.wcs.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getAllUsers (){
        return userService.getAllUser();
    }


    @GetMapping("/me")
    public ResponseEntity<?>  getMe(Authentication authentication){
        if(authentication== null){ return new ResponseEntity<>(
                "Not Connected",
                HttpStatus.UNAUTHORIZED);}
        try {
            return new ResponseEntity<>(
                    userService.getMe(authentication.getName()),
                    HttpStatus.OK);

        } catch (UserException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getUserName")
    public String getName (Authentication authentication){
        return authentication.getName();
    }

    @GetMapping("/getRoles")
    public List<String> getRoles (Authentication authentication){
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority grantedAuthority :authentication.getAuthorities()){
            roles.add(grantedAuthority.getAuthority());
        }
        return roles;
    }

    @PostMapping("/addVideoToFavorites")
//    public ResponseEntity<List<Video>> addVideoToFavorites(@RequestBody Long idVideo, Authentication auth){
    public ResponseEntity<List<Video>> addVideoToFavorites(@RequestBody Map<String, String> request, Authentication auth){
        System.out.println("long" + request.get("id"));
        return new ResponseEntity<>(
                userService.addVideoToFavorites(Long.parseLong(request.get("id")), auth.getName()),
                HttpStatus.OK
        );
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser (Authentication auth, @RequestBody User user){
        try {
            return new ResponseEntity<>(userService.updateUser(auth.getName(), user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteUser (Authentication auth) {
        userService.deleteUser(auth.getName());
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }
}
