package com.wcs.Security.controllers;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.Role;
import com.wcs.Security.models.User;
import com.wcs.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getAllUsers (){
        return userService.getAllUser();
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

    // MODIFIER SON NOM ET PRENOM : updateFirstName(), updateLastName()


    // MODIFIER SON MOT DE PASSE : updatePassword()

    // SUPPRIMER SON COMPTE USER : deleteUser()

    // MODIFIER LE ROLE D'UN USER : updateRole()

    // CREER UN ADMIN (A PARTIR DE L'ESPACE ADMIN) : createAdminFromAdminSession()

    // EXPORTER LISTE DES USERS DANS UN FICHIER CSV : downloadListUsers()

    // REINITIALISER SON MOT DE PASSE : resetPassword()

}
