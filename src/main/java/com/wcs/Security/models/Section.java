package com.wcs.Security.models;

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
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //JOINTURE SECTION & VIDEO
    @OneToMany(
            mappedBy = "section",
            cascade = CascadeType.ALL
    )
    private List<Video> videos = new ArrayList<>();
}
