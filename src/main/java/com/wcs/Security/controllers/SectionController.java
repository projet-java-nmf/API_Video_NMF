package com.wcs.Security.controllers;

import com.wcs.Security.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    SectionService sectionService;


}
