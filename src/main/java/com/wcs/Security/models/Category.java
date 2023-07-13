package com.wcs.Security.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
    private String name;

    @JsonIgnoreProperties("category")
    @ManyToMany(mappedBy = "categories")
    private List<Video> videos = new ArrayList<>();
}
