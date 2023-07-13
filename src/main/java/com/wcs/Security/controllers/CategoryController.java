package com.wcs.Security.controllers;

import com.wcs.Security.models.Category;
import com.wcs.Security.models.Video;
import com.wcs.Security.repositories.CategoryRepository;
import com.wcs.Security.repositories.VideoRepository;
import com.wcs.Security.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VideoRepository videoRepository;

    // ADD UNE CATEGORIE
    @PostMapping("/create-category")
    public Category addCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    //GET TOUTES LES CATEGORIES
    @GetMapping("")
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    // GET UNE CATEGORIE
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.get();
    }

    // GET UNE CATEGORY PAR SON NOM => getCategoryByName(String Name)
    @GetMapping("/by-name/{name}")
    public Category getCategoryByName(@PathVariable String name){
        return categoryRepository.findByName(name).get();
    }

    //MODIFIER LE NOM D'UNE CATEGORIE : updateVideoCategory()
    @PutMapping("/update-category")
    public Category updateVideoCategory(@ModelAttribute Category category){
        return categoryRepository.save(category);
    }

    // AJOUT DE VIDEOS A UNE CATEGORIE
    /*@PutMapping("/{name}/add-video")
    public void addVideo(@PathVariable Long name, @RequestParam Long idVideo) {
        Category category = categoryRepository.findByName("name").get();
        Video video = videoRepository.findById(idVideo).get();
        video.getCategories().add(category);
        videoRepository.save(video);
    }*/

}
