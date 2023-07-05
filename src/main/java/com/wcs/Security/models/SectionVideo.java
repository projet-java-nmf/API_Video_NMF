package com.wcs.Security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //JOINTURE SECTION & VIDEO
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private  Video video;

    private boolean isInHero;
    private boolean isInStaticCarousel;
    private boolean isInDynamicCarousel;
    private boolean isInGrid;

}
