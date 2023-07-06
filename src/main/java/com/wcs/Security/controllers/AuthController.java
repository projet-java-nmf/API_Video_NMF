package com.wcs.Security.controllers;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.exceptions.UserException;
import com.wcs.Security.models.User;
import com.wcs.Security.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-up-user")
    public ResponseEntity<String> createUser (@RequestBody User user){
        try {
            User result = userService.createUser(user);
            userService.addRoleToUser(result.getEmail(), RoleName.USER);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sign-up-admin")
    public ResponseEntity<String> createAdmin (@RequestBody User user){
        try {
            User result = userService.createUser(user);
            userService.addRoleToUser(result.getEmail(), RoleName.ADMIN);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("/login")
    public ResponseEntity<?> login (@RequestBody Map<String, String> request){

        try{
            String response = userService.login(request.get("email"), request.get("password"));
            if(response == null){
                return new ResponseEntity<>(
                        "Email ou le mot de passe sont incorrect",
                        HttpStatus.UNAUTHORIZED
                );
            }
            if(response.equals("-1") ){
                return new ResponseEntity<>(
                        "Vous n'avez pas encore vérifié votre email",
                        HttpStatus.UNAUTHORIZED
                );
            }else{
                return new ResponseEntity<>(
                        response,
                        HttpStatus.OK
                );

            }
        }
        catch(Exception e){
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/email-confirmation/{email}")
    boolean emailConfirmation(@PathVariable String email, @RequestBody Map <String , Integer> request){

        return userService.emailConfirmation(email, request.get("code"));
    }
}
