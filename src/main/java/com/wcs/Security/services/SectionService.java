package com.wcs.Security.services;

import com.wcs.Security.models.Section;
import com.wcs.Security.models.Video;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    Section createSection(Section Section);
    List<Section> getAllSection();
    Optional<Section> getSectionById(Long id);
    Section updateSection(Section section);
    List<Section> removeSection(Long id);
    List<Video> addVideoToSectionById(Long idSection, Long idVideo);
    List<Video> removeVideoToSection(Long idSection, Long idVideo);
}
