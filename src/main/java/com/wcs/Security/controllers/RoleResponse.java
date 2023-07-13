package com.wcs.Security.controllers;

import com.wcs.Security.enums.RoleName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleResponse {
    private RoleName name;
}
