package com.wcs.Security.config;

import com.wcs.Security.enums.GenderName;
import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.Gender;
import com.wcs.Security.models.Role;
import com.wcs.Security.models.User;
import com.wcs.Security.repositories.GenderRepository;
import com.wcs.Security.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepo;

    private final GenderRepository genderRepo;

    public DataInitializer(
            RoleRepository roleRepository,
            GenderRepository genderRepository
                           ) {
        this.roleRepo = roleRepository;
        this.genderRepo = genderRepository;
    }

    @Override
    public void run(String... args) {
        for (GenderName gender : GenderName.values()) {
            genderRepo.save(new Gender(null, gender));
        }

        for (RoleName role : RoleName.values()) {
          List<User> users = new ArrayList<>();
            roleRepo.save(new Role(null, role,users));
        }


    }
}

