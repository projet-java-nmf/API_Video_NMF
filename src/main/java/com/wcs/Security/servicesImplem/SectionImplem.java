package com.wcs.Security.servicesImplem;

import com.wcs.Security.models.Section;
import com.wcs.Security.models.Video;
import com.wcs.Security.repositories.SectionRepository;
import com.wcs.Security.repositories.VideoRepository;
import com.wcs.Security.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionImplem implements SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    VideoRepository videoRepository;

    @Override
    public Section createSection(Section Section) {
        return sectionRepository.save(Section);
    }

    @Override
    public List<Section> getAllSection() {
        return sectionRepository.findAll();
    }

    @Override
    public Optional<Section> getSectionById(Long id) {
        return sectionRepository.findById(id);
    }

    @Override
    public Section updateSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public List<Section> removeSection(Long id) {
        sectionRepository.deleteById(id);
        return sectionRepository.findAll();
    }

    @Override
    public List<Video> addVideoToSectionById(Long idSection, Long idVideo) {
        Optional<Section> section = sectionRepository.findById(idSection);
        section.get().getVideos().add(videoRepository.findById(idVideo).get());
        sectionRepository.save(section.get());
        return section.get().getVideos();
    }

    @Override
    public List<Video> removeVideoToSection(Long idSection, Long idVideo) {
        List<Video> videos = sectionRepository.findById(idSection).get().getVideos();
        Optional<Section> section = sectionRepository.findById(idSection);
        Optional<Section> result;
        for (Video vid : videos) {
            if (vid.getId() == idVideo)
                videoRepository.deleteById(idVideo);
        }
        sectionRepository.save(section.get());
        return section.get().getVideos();
    }
}
