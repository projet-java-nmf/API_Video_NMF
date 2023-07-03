package com.wcs.Security.controllers;

import com.wcs.Security.models.Video;
import com.wcs.Security.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoController {

    @Autowired
    VideoService videoService;

    // SAVE UNE VIDEO
    @PostMapping("/videos")
    public ResponseEntity<Video> createVideo(@RequestBody Video video){
        return
                new ResponseEntity<>(
                        videoService.createVideo(video),
                        HttpStatus.CREATED
                );
    }

    // GET TOUTES LES VIDEOS
    @GetMapping("videos")
    public ResponseEntity< List<Video> > getAllVideo(){
        return
                new ResponseEntity<>(
                        videoService.getAllVideos(),
                        HttpStatus.OK
                );
    }

    // GET UNE VIDEO
    @GetMapping("videos/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        Video video = videoService.getVideoById(id);
        if (video != null){
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

}
