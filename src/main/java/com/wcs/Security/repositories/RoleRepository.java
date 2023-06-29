package com.wcs.Security.repositories;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role save (Role role);
    Optional<Role> findByName (RoleName name);
}
