package com.wcs.Security.controllers;

import com.wcs.Security.dto.RoleDto;
import com.wcs.Security.dto.UserDto;
import com.wcs.Security.enums.RoleName;
import com.wcs.Security.exceptions.JwtException;
import com.wcs.Security.exceptions.UserException;
import com.wcs.Security.models.Role;
import com.wcs.Security.models.User;
import com.wcs.Security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    UserService userService;

    private final ModelMapper modelMapper;

    @PostMapping("/sign-up-user")
    public ResponseEntity<?> createUser (@RequestBody User user){
        try {
            User result = userService.createUser(user);
            result =  userService.addRoleToUser(result.getEmail(), RoleName.USER);
            return new ResponseEntity<>(
                 RegisterResponse.builder()
                            .email(result.getEmail())
                            .build(),
                       HttpStatus.CREATED);
        }catch (UserException e){
            return new ResponseEntity<>( e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>( "Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sign-up-admin")
    public ResponseEntity<?> createAdmin (@RequestBody User user){
        try {
            User result = userService.createUser(user);
            result = userService.addRoleToUser(result.getEmail(), RoleName.ADMIN);
            return new ResponseEntity<>(
                  //  "Success",
                    RegisterResponse.builder()
                            .email(result.getEmail())
                            .build(),
                    HttpStatus.CREATED);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("/login")
    public ResponseEntity<?> login (@RequestBody Map<String, String> form){
        try{
            return new ResponseEntity<>(
                 userService.login(form.get("email"), form.get("password")),
                HttpStatus.OK

                );
            }catch (UserException e){
            Map<String , Object> body = new HashMap<>();
            body.put("message" , e.getMessage());
            return new ResponseEntity<>(
                    body,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/email-confirmation/{email}")
    private  ResponseEntity<?> emailConfirmation(@PathVariable String email, @RequestBody Map <String , Integer> request) {
        Map<String, Object> body = new HashMap<>();
        try {
            if (userService.emailConfirmation(email, request.get("code"))) {
                body.put("message", "Code correct");
                return
                        new ResponseEntity<>(
                                body,
                                HttpStatus.OK
                        );
            } else {
                body.put("message", "Code inccorrect");
                return new ResponseEntity<>(
                        body,
                        HttpStatus.OK
                );
            }
        } catch (UserException e) {
            body.put("message", e.getMessage());
            return new ResponseEntity<>(
                    body,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("reset-password-request")
    ResponseEntity<?> resetPasswordRequest(@RequestBody Map<String , String> request){
        Map<String , Object> body = new HashMap<>();
        try{
            if( userService.resetPasswordRequest(request.get("email"))){
                body.put("message","un email de réinitialisation vous a été envoyé");
                return new ResponseEntity<>(
                        body,
                        HttpStatus.OK
                );
            }else{
                body.put("message","Une erreur est survenue, veuillez ressayer plus tard !");
                return new ResponseEntity<>(
                        body,
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        }
        catch (UserException e){
            body.put("message",e.getMessage());
            return new ResponseEntity<>(
                    body,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("reset-password")
    ResponseEntity<?> resetPassword (@RequestBody Map<String ,String> request) {
        Map<String , Object> body = new HashMap<>();
        try {
            userService.resetPassword(request.get("token"), request.get("password"));
            body.put("message","Le mot de passe a été modifié");
            return new ResponseEntity<>(
                    body, HttpStatus.OK
            );
        }catch (UserException e){
            body.put("message", e.getMessage());
            return new ResponseEntity<>(
                    body,
                    HttpStatus.NOT_FOUND

            );
        }catch(JwtException e){
            body.put("message", "Le lien n'est pas valide !");
            return new ResponseEntity<>(
                    body,
                    HttpStatus.NOT_FOUND
            );
        }
    }
    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private List<UserDto> convertToListDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(convertToDto(user));
        }
        return userDtos;
    }


}
