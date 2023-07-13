package com.wcs.Security.repositories;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    User save (User user);
    Optional<User> findByEmail (String email);
    List<User> findAll ();
    Optional<User> findByFirstname (String firstname);

    List<User> findByRoles_name(RoleName roleName);
}
