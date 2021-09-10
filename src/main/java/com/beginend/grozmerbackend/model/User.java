package com.beginend.grozmerbackend.model;

import com.beginend.grozmerbackend.entity.RoleEntity;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String login;
    private String password;
    private RoleEntity role;
    private Long roleId;
    private String name;
}
