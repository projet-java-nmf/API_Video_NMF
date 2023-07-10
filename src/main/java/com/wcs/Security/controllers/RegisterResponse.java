package com.wcs.Security.controllers;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterResponse {
    private String email;
}
