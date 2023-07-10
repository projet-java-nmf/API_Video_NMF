package com.wcs.Security.servicesImplem;

import com.wcs.Security.models.Category;
import com.wcs.Security.models.Video;
import com.wcs.Security.repositories.CategoryRepository;
import com.wcs.Security.repositories.VideoRepository;
import com.wcs.Security.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoImplem implements VideoService {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Video createVideo(Video video) {
        List<Category> categories = new ArrayList<>();
        Optional<Category> category;
        for(Category cat : video.getCategories()){
            category = categoryRepository.findById(cat.getId());
            if(category.isPresent())
                categories.add(categoryRepository.findById(cat.getId()).get());
        }
        video.setCategories(categories);
        return videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> getVideosByCategory(Long idCategory) {
        List<Video> videos = videoRepository.findAll();
        List<Video> result = new ArrayList<>();
        for(Video vid : videos){
            for (Category cat : vid.getCategories()){
                if(cat.getId()== idCategory){
                    result.add(vid);
                    break;
                }
            }
        }
        return result;
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
