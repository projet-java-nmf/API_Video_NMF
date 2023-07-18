package com.wcs.Security.servicesImplem;

import com.wcs.Security.enums.RoleName;
import com.wcs.Security.exceptions.RoleException;
import com.wcs.Security.exceptions.UserException;
import com.wcs.Security.models.Role;
import com.wcs.Security.models.User;
import com.wcs.Security.repositories.RoleRepository;
import com.wcs.Security.repositories.UserRepository;
import com.wcs.Security.services.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleImplem implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public List<Role> getRoles() {
           return roleRepository.findAll();
    }

  /*  @Override
    public List<Role> getRolesByUser(Authentication authentication) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRole;
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            optionalRole = roleRepository.findByName(RoleName.valueOf(grantedAuthority.getAuthority()));
            if (optionalRole.isPresent()) {
                roles.add(optionalRole.get());
            }
        }
        return roles;
    }
*/
    @Override
    public List<Role> getRolesByUser(Long id) throws UserException {
        List<Role> result = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            result = user.get().getRoles();
            return result;
        }
        throw new UserException("User not found");

    }

    @Override
    public Optional<Role> getRoleByName(String name) throws RoleException {
        if(!isValideRoleName(name)){throw  new RoleException("Role is not valid");}
        return roleRepository.findByName(RoleName.valueOf(name));
    }

    @Override
    public Optional<User> updateRolesUser(String roleName, String id) throws UserException, RoleException {
        if(!isValideRoleName(roleName)){throw  new RoleException("Role is not valid");}
        Optional<User> user = userRepository.findById(Long.parseLong(id));
        Optional<Role> roleResult;
        if (user.isPresent()) {
            // Checke si le role est pas présent d'abord
            List<Role> userRoles = getRolesByUser(Long.parseLong(id));
            boolean result = false;
            for (Role role : userRoles) {
                if (role.getName().name().equals(roleName)) {
                    System.out.println("titi");
                    result = true;
                    break;
                }
            }
            if (!result) {
                roleResult = roleRepository.findByName(RoleName.valueOf(roleName));
                if (roleResult.isPresent()) {
                    user.get().getRoles().add(roleResult.get());
                    return user;
                } else {
                    throw new RoleException("Role not found ");
                }

            } else {
                throw new RoleException("Role already exist");
            }
        }
        throw new UserException("User not found");
    }

    @Override
    public void deleteRolesUser(String roleName, String id)  throws  RoleException{
        if(!isValideRoleName(roleName)){throw  new RoleException("Role is not valid");}
        Optional<User> user = userRepository.findById(Long.parseLong(id));
        Optional<Role> role;
        if (user.isPresent()) {
            // Checke si le role est pas présent d'abord
            List<Role> userRoles = null;
            try {
                userRoles = getRolesByUser(Long.parseLong(id));
                userRoles.removeIf(p -> p.equals(roleRepository.findByName(RoleName.valueOf(roleName)).get()));
                user.get().setRoles(userRoles);
                userRepository.save(user.get());

            } catch (UserException e) {

            }

        }

    }

    @Override
    public List<User> getUsersByRole(String role) throws  RoleException {
        if(!isValideRoleName(role)){throw  new RoleException("Role is not valid");}
        return userRepository.findByRoles_name(RoleName.valueOf(role));
    }

    public boolean isValideRoleName(String name) {
        for (RoleName roleName : RoleName.values()) {
            if (roleName.name().equals(name)) {
                return true;
            }
        }
        return false;

    }
}
