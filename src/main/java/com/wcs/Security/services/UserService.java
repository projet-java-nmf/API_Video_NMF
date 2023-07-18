package com.wcs.Security.services;

import com.wcs.Security.enums.RoleName;

import com.wcs.Security.exceptions.JwtException;
import com.wcs.Security.exceptions.UserException;

import com.wcs.Security.exceptions.UserNotFound;

import com.wcs.Security.models.User;
import com.wcs.Security.models.Video;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    User createUser (User user) throws Exception;
    List<User> getAllUser ();
    Optional<User> getUserByEmail (String email);

    User addRoleToUser (String email, RoleName roleName) throws UserException;
    Map<String, Object> login (String email, String password) throws UserException;

    User updateUser(String email, User user);
    @Transactional()
    void deleteUser(String email);
    List<Video> addVideoToFavorites(Long idVideo, String email);
    boolean emailConfirmation(String email, int code) throws UserException;

    boolean resetPasswordRequest(String email) throws UserException;

    void resetPassword(String token , String password) throws UserException, JwtException;


    User getMe(String name) throws UserException;
}
