package com.beginend.grozmerbackend.dto;

import com.beginend.grozmerbackend.entity.UserEntity;
import com.beginend.grozmerbackend.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    public List<User> transferEntityToModelList(List<UserEntity> entities){
        return entities.stream().map(entity-> transferEntityToModel(entity)).collect(Collectors.toList());
    }

    public User transferEntityToModel(UserEntity entity){
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setLogin(entity.getLogin());
        user.setPassword(entity.getPassword());
        if(entity.getRole() != null){
            user.setRole(entity.getRole());
        }
        user.setRoleId(entity.getRole_id());
        return user;
    }

    public UserEntity transferModelToEntity(User model){
        UserEntity user = new UserEntity();
        user.setId(model.getId());
        user.setName(model.getName());
        user.setLogin(model.getLogin());
        user.setPassword(model.getPassword());
        if(model.getRole() != null){
            user.setRole_id(model.getRole().getId());
        }
        user.setRole_id(model.getRoleId());
        return user;
    }
}
