package com.wcs.Security.servicesImplem;

import com.wcs.Security.repositories.AdBannerRepository;
import com.wcs.Security.services.AdBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdBannerImplem implements AdBannerService {

    @Autowired
    AdBannerRepository adBannerRepository;

}
