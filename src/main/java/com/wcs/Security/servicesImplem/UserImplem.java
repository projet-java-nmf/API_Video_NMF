package com.wcs.Security.servicesImplem;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.models.Role;
import com.wcs.Security.models.User;
import com.wcs.Security.repositories.RoleRepository;
import com.wcs.Security.repositories.UserRepository;
import com.wcs.Security.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserImplem implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User createUser(User user) {
        String password = user.getPassword();
        String passwordEncoded = passwordEncoder.encode(password);
        user.setPassword(passwordEncoded);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void addRoleToUser(String email, RoleName roleName) {
        Optional<Role> role = roleRepository.findByName(roleName);
        Optional<User> user = userRepository.findByEmail(email);

        if (role.isPresent() && user.isPresent()){
            user.get().getRoles().add(role.get());
        }
    }

    @Override
    public String login (String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );
            return jwtService.generateToken(user.get());
        }
        return "user not found";
    }
}
