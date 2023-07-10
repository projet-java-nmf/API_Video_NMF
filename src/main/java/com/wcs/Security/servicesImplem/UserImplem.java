package com.wcs.Security.servicesImplem;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.exceptions.UserException;
import com.wcs.Security.models.Role;
import com.wcs.Security.models.User;
import com.wcs.Security.models.Video;
import com.wcs.Security.repositories.RoleRepository;
import com.wcs.Security.repositories.UserRepository;
import com.wcs.Security.repositories.VideoRepository;
import com.wcs.Security.services.EmailService;
import com.wcs.Security.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UserImplem implements UserService {

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JwtService jwtService;

    @Autowired
    EmailService emailService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User createUser(User user) throws UserException {
     // Checkif is already created

        Optional<User> userChecked = userRepository.findByEmail(user.getEmail());
        if(userChecked.isPresent()){
            throw  new UserException("L'utilisateur entré existe déja. Veuillez entrez un autre utilisateur .");
        }else{
            String password = user.getPassword();
            String passwordEncoded = passwordEncoder.encode(password);
            user.setPassword(passwordEncoded);
            Random random = new Random();

            int code =  random.nextInt(1000,10000);
            user.setVerificationEmailCode(
                    code
            );
            if(!user.isEmailVerified()) {
                emailService.sendEmail(
                        user.getEmail(),
                        "Vérification de l'adresse email",
                        "Voila le code de vérification de votre email : " + code
                );
            }
            return userRepository.save(user);
        }


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
    public void addRoleToUser(String email, RoleName roleName) throws Exception {
        Optional<Role> role = roleRepository.findByName(roleName);
        Optional<User> user = userRepository.findByEmail(email);
        System.out.println(role.get().getName().name());
        if (role.isPresent() && user.isPresent()) {
            user.get().getRoles().add(role.get());
        } else {
            throw new Exception();
        }
    }

    @Override
    public String login(String email, String password) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (user.get().isEmailVerified()) {
                authenticationManager
                        .authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        email,
                                        password
                                )
                        );
                return jwtService.generateToken(user.get());
            }
            return "-1";

        } else {
           throw new Exception();
        }
    }

    @Override
    public boolean emailConfirmation(String email, int code) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            int codeUser = user.get().getVerificationEmailCode();
            if(code == codeUser){
                user.get().setEmailVerified(true);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public User updateUser(String email, User user) {
        Optional<User> userInData = userRepository.findByEmail(email);

        if (userInData.isPresent()) {
            if (user.getFirstname() != null) {
                if (!user.getFirstname().equals("")) {
                    userInData.get().setFirstname(user.getFirstname());
                }
            }
            if (user.getLastname() != null) {
                if (!user.getLastname().equals("")) {
                    userInData.get().setLastname(user.getLastname());
                }
            }
            if (user.getEmail() != null) {
                if (!user.getEmail().equals("")) {
                    userInData.get().setEmail(user.getEmail());
                }
            }
        }
        return userInData.get();
    }

    @Override
    @Transactional()
    public void deleteUser(String email) {
        try {
            userRepository.deleteById(userRepository.findByEmail(email).get().getId());
        } catch (EmptyResultDataAccessException ex) {
            throw new RuntimeException("Utilisateur introuvable avec l'identifiant : " + email);
        }
    }

    @Override
    public List<Video> addVideoToFavorites(Long idVideo, String email) {
        Optional<User> userInData = userRepository.findByEmail(email);
        if (userInData.isPresent()) {
                userInData.get().getFavoritesList().add(videoRepository.findById(idVideo).get());
                userRepository.save(userInData.get());
        }
        return userInData.get().getFavoritesList();
    }
}
