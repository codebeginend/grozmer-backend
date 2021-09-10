package com.beginend.grozmerbackend.service;

import com.beginend.grozmerbackend.dao.IRoleDao;
import com.beginend.grozmerbackend.entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private IRoleDao roleDao;

    public RoleService(IRoleDao roleDao){
        this.roleDao = roleDao;
    }

    public List<RoleEntity> findAll(){
        return this.roleDao.findAll();
    }
}
