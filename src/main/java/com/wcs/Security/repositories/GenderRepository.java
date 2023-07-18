package com.wcs.Security.repositories;


import com.wcs.Security.enums.GenderName;
import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.Gender;
import com.wcs.Security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Long> {

    Gender save (Gender gender);

    List<Gender> findAll();
    Optional<Gender> findByName (GenderName name);

}
