package com.wcs.Security.controllers;

import com.wcs.Security.models.Video;
import com.wcs.Security.repositories.VideoRepository;
import com.wcs.Security.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    @Autowired
    VideoRepository videoRepository;

    // SAVE UNE VIDEO
    @PostMapping("/upload-video")
    public ResponseEntity<Video> createVideo(@RequestBody Video video){
        return
                new ResponseEntity<>(
                        videoService.createVideo(video),
                        HttpStatus.CREATED
                );
    }

    // GET TOUTES LES VIDEOS
    @GetMapping("")
    public ResponseEntity< List<Video> > getAllVideo(){
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
        if (video != null){
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

    // SUPPRIMER UNE VIDEO : deleteVideo()
    @DeleteMapping("/delete")
    public String deleteVideo(@RequestParam Long id) {
        videoRepository.deleteById(id);

        return null;
    }
  
    // MODIFIER UNE VIDEO : updateVideo()
    @PutMapping("/update-video")
    public String updateVideo(@ModelAttribute Video video) {
        videoRepository.save(video);
        return null;
    }

    //METHODE AVEC GET => MODIFIER L'ACCES PUBLIC/PRIVE D'UNE VIDEO
    @PutMapping("/update-video2")
    public Video updateVideo(Long id, String title, String description, String publicationDate, boolean Privated, boolean hasTeaser) {
        Video videoToUpdate = videoRepository.findById(id).get();
        //videoToUpdate.setDescription(description);
        //videoToUpdate.setPublicationDate(publicationDate);
        videoToUpdate.setPrivated(Privated);
        //videoToUpdate.setHasTeaser(hasTeaser);
        return videoRepository.save(videoToUpdate);
    }

    //GET TOUTES LES VIDEOS PUBLIC
    @GetMapping("/public/{isPrivate}")
    public List<Video> getAllPublicVideos(@PathVariable String isPrivate){
        return videoRepository.findByPrivated(  isPrivate.equals("false") ? false : true);
    }

    // LIRE 20 SECONDES D'UNE VIDEO (TEASER VIDEO LORS D'UN HOVER) : readTeaserVideo()

}
