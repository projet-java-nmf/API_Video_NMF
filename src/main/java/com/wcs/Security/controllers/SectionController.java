package com.wcs.Security.controllers;

import com.wcs.Security.models.Section;
import com.wcs.Security.models.Video;
import com.wcs.Security.repositories.SectionRepository;
import com.wcs.Security.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    SectionService sectionService;

    // ADD A SECTION
    @PostMapping("/create-section")
    public Section createSection(@RequestBody Section section){
        return sectionService.createSection(section);
    }

    // GET ALL SECTIONS
    @GetMapping("")
    public List<Section> getAllSection(){
        return sectionService.getAllSection();
    }

    // GET A SECTION BY ID
    @GetMapping("/{id}")
    public Optional<Section> getSectionById(@PathVariable Long id){
        return sectionService.getSectionById(id);
    }

    // UPDATE A SECTION
    @PutMapping("/update-section")
    public Section updateSection(@ModelAttribute Section section){
        return sectionService.createSection(section);
    }

    // DELETE A SECTION BY ID
    @DeleteMapping("/remove-section/{id}")
    public List<Section> removeSection(@PathVariable Long id){
        sectionService.removeSection(id);
        return sectionService.getAllSection();
    }

    // ADD A VIDEO BY ID
    @GetMapping("/add-video-to-section")
    public List<Video> addVideoToSectionById(@RequestParam Long idSection, @RequestParam Long idVideo){
        return sectionService.addVideoToSectionById(idSection, idVideo);
    }

    // DELETE A VIDEO BY ID
    @DeleteMapping("/remove-video-to-section")
    public List<Video> removeVideoToSection(@RequestParam Long idSection, @RequestParam Long idVideo){
        return sectionService.removeVideoToSection(idSection, idVideo);
    }

}
