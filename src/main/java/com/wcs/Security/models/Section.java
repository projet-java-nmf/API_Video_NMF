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
    private String name;
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "section_video",
            joinColumns = {
                @JoinColumn(name = "section_id")
            }, inverseJoinColumns = {
                @JoinColumn(name = "video_id")
            }
    )
    private List<Video> videos = new ArrayList<>();
}
