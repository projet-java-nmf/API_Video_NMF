package com.wcs.Security.servicesImplem;

import com.wcs.Security.models.Video;
import com.wcs.Security.repositories.VideoRepository;
import com.wcs.Security.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoImplem implements VideoService {

    @Autowired
    VideoRepository videoRepository;

    @Override
    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> getAllPublicVideos() {
        return null;
    }

    @Override
    public Video getVideoById(Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()){
            return video.get();
        }
        return null;
    }

}
