package com.beginend.grozmerbackend.web.controller;

import com.beginend.grozmerbackend.entity.RoleEntity;
import com.beginend.grozmerbackend.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping("all")
    public List<RoleEntity> findAll(){
        return this.roleService.findAll();
    }
}
