package com.wcs.Security.services;

import com.wcs.Security.exceptions.RoleException;
import com.wcs.Security.exceptions.UserException;
import com.wcs.Security.models.Role;
import com.wcs.Security.models.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface RoleService {


    List<Role> getRoles ();
    List<Role> getRolesByUser (Authentication authentication) throws RoleException;

    List<Role> getRolesByUser (Long id) throws UserException;

    Optional<Role> getRoleByName (String name) throws  RoleException;

    Optional<User> updateRolesUser(String roleName, String id) throws UserException, RoleException;

    void deleteRolesUser(String role, String id) throws  RoleException;

    List<User> getUsersByRole(String role) throws RoleException;

}
