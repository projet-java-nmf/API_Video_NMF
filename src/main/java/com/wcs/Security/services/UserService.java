package com.wcs.Security.services;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.User;
import com.wcs.Security.models.Video;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser (User user);
    List<User> getAllUser ();
    Optional<User> getUserByEmail (String email);
    void addRoleToUser (String email, RoleName roleName) throws Exception;
    String login (String email, String password) throws Exception;
    User updateUser(String email, User user);
    @Transactional()
    void deleteUser(String email);
    List<Video> addVideoToFavorites(Long idVideo, String email);
}
