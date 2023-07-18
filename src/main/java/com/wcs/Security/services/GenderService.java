package com.wcs.Security.services;

import com.wcs.Security.models.Gender;
import com.wcs.Security.models.User;


import java.util.List;

public interface GenderService {

    List<Gender> getGenders ();
    List<User> getUsersByGender(String gender);
}
