package com.wcs.Security.dto;

import com.wcs.Security.enums.RoleName;
import lombok.Builder;
import lombok.Data;


@Data
public class RoleDto {
    private RoleName name;
}
