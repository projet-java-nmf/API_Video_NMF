package com.wcs.Security.repositories;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role save (Role role);

    List<Role> findAll();
    Optional<Role> findByName (RoleName name);

}


