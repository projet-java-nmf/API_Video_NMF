package com.wcs.Security.repositories;

import com.wcs.Security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save (User user);
    Optional<User> findByEmail (String email);
    List<User> findAll ();
}
