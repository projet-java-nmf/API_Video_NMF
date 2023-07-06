package com.wcs.Security.controllers;

import com.wcs.Security.models.Video;
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

    //GET TOUTES LES VIDEOS PUBLIC
  /*  @GetMapping("/{isPrivate}")
    public List<Video> getAllPublicVideos(@PathVariable Boolean isPrivate){
        //return videoService.getAllPublicVideos(isPrivate);
        return null;
    }
*/
    // MODIFIER UNE VIDEO : updateVideo()

    // SUPPRIMER UNE VIDEO : deleteVideo()

    // MODIFIER L'ACCES PUBLIC/PRIVE D'UNE VIDEO : updateIsPrivate()

    // LIRE 20 SECONDES D'UNE VIDEO (TEASER VIDEO LORS D'UN HOVER) : readTeaserVideo()

}
