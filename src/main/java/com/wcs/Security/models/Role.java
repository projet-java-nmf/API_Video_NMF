package com.wcs.Security.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wcs.Security.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private RoleName name;

   @JsonIgnoreProperties("roles")
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
