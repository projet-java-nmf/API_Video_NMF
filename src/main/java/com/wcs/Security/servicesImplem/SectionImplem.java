package com.wcs.Security.servicesImplem;

import com.wcs.Security.repositories.SectionRepository;
import com.wcs.Security.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionImplem implements SectionService {

    @Autowired
    SectionRepository sectionRepository;

}
