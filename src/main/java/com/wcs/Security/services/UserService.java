package com.wcs.Security.services;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser (User user);
    List<User> getAllUser ();
    Optional<User> getUserByEmail (String email);
    void addRoleToUser (String email, RoleName roleName) throws Exception;
    String login (String email, String password) throws Exception;
    User updateUser(Long id, User user);
    String getAuthenticatedUserEmail();


}
