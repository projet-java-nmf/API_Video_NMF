package com.wcs.Security.servicesImplem;

import com.wcs.Security.repositories.CategoryRepository;
import com.wcs.Security.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImplem implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

}
