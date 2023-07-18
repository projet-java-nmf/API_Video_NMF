package com.wcs.Security.controllers;

import com.wcs.Security.exceptions.RoleException;
import com.wcs.Security.exceptions.UserException;
import com.wcs.Security.models.Role;
import com.wcs.Security.dto.RoleDto;
import com.wcs.Security.models.User;
import com.wcs.Security.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("admin/roles")
@RequiredArgsConstructor
public class RoleController {

    @Autowired
    RoleService roleService;

    private final ModelMapper modelMapper;

    @PostMapping("/user")
    public ResponseEntity<?> getRolesByUser(@RequestBody Map<String, String> request) {
        try {
            return new ResponseEntity<>(
                    convertToListDto(roleService.getRolesByUser(Long.parseLong(request.get("id")))),
                    HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("")
    public ResponseEntity<?> getRoles() {
        return new ResponseEntity<>(
                convertToListDto(roleService.getRoles()),
                HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateRolesUser(@RequestBody Map<String, String> request) {
        try {
            Optional<User> user = roleService.updateRolesUser(request.get("role"), request.get("id"));
            if (user != null && user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RoleException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteRolesUser(@RequestBody Map<String, String> request) {
        try {
            roleService.deleteRolesUser(request.get("role"), request.get("id"));
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (RoleException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> getAllUserByRole(@RequestBody Map<String, String> request) {
        try {
            Map<String , Object> body = new HashMap<>();
            body.put("response", roleService.getUsersByRole(request.get("role")));
            return new ResponseEntity<>(body , HttpStatus.OK);
        } catch (RoleException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private RoleDto convertToDto(Role role) {
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        return roleDto;
    }

    private List<RoleDto> convertToListDto(List<Role> roles) {
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(convertToDto(role));
        }
        return roleDtos;
    }

}
