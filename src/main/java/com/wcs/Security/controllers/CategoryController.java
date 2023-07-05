package com.wcs.Security.controllers;

import com.wcs.Security.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //CREER UNE CATEGORIE : createCategory()

    //MODIFIER LE NOM D'UNE CATEGORIE : updateVideoCategory()

    //SUPPRIMER UNE CATEGORIE : deleteCategory()

}
