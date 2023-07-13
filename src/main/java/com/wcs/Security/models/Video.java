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
public class Video {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String publicationDate;

    private boolean isPrivate;
    private boolean hasTeaser;

    //JOINTURE MANY TO MANY SIMPLE VIDEO & CATEGORY
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "video_category",
            joinColumns = {
                    @JoinColumn(name = "video_id")
            },inverseJoinColumns = {
                    @JoinColumn(name = "category_id")
            }
    )
    private List<Category> categories = new ArrayList<>();


    @ManyToMany(mappedBy = "favoritesList")
    private List<User> users = new ArrayList<>();


    @ManyToMany(mappedBy = "videos")
    private List<Section> sections = new ArrayList<>();



    //L'url de la video : "l'endroit où vous allez stocker la vidéo"
}
