package com.wcs.Security.controllers;

import com.wcs.Security.models.Category;
import com.wcs.Security.models.Video;
import com.wcs.Security.repositories.CategoryRepository;
import com.wcs.Security.repositories.VideoRepository;
import com.wcs.Security.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("videos")
public class VideoController {
    @Autowired
    VideoService videoService;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    CategoryRepository categoryRepository;

    // ADD UNE VIDEO
    @PostMapping("/upload-video")
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        return
                new ResponseEntity<>(
                        videoService.createVideo(video),
                        HttpStatus.CREATED
                );
    }

    // GET TOUTES LES VIDEOS
    @GetMapping("")
    public ResponseEntity<List<Video>> getAllVideo() {
        return
                new ResponseEntity<>(
                        videoService.getAllVideos(),
                        HttpStatus.OK
                );
    }

    // GET UNE VIDEO
    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        System.out.println("1");
        Video video = videoService.getVideoById(id);
        if (video != null) {
            System.out.println("2");
            return
                    new ResponseEntity<>(
                            video,
                            HttpStatus.OK
                    );
        } else {
            return
                    new ResponseEntity<>(
                            null,
                            HttpStatus.NOT_FOUND
                    );
        }
    }

    // SUPPRIMER UNE VIDEO : removeVideo()
    @DeleteMapping("/remove")
    public String removeVideo(@RequestParam Long id){
        videoRepository.deleteById(id);
        return null;
    }

    // MODIFIER UNE VIDEO : updateVideo()
    @PutMapping("/update-video")
    public Video updateVideo(@ModelAttribute Video video){
        return videoRepository.save(video);
    }

    //GET TOUTES LES VIDEOS PUBLIC
    @GetMapping("/public/{isPrivate}")
    public List<Video> getAllPublicVideos(@PathVariable String isPrivate) {
        return videoRepository.findByPrivated(isPrivate.equals("false") ? false : true);
    }

    // ADD CATEGORY TO VIDEO BY Id => addCategoryToVideoByID()
    @GetMapping("/add-category")
    public void addCategoryToVideoById(@RequestParam Long videoId, @RequestParam Long categoryId) {
        Video video = videoRepository.findById(videoId).get();
        Category category = categoryRepository.findById(categoryId).get();
        video.getCategories().add(category);
        //category.getVideos().add(video);
        videoRepository.save(video);
        //categoryRepository.save(category);
    }

    // GET VIDEOS BY CATEGORY => getVideosByCategory(category)
    @GetMapping("/by-category/{idCategory}")
    public ResponseEntity<List<Video>> getVideosByCategory(@PathVariable Long idCategory) {
        return
                new ResponseEntity<>(
                        videoService.getVideosByCategory(idCategory),
                        HttpStatus.OK
                );
        }
}
