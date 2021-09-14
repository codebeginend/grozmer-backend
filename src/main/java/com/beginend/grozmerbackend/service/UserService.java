package com.beginend.grozmerbackend.service;

import com.beginend.grozmerbackend.dao.IUserDao;
import com.beginend.grozmerbackend.dto.UserDto;
import com.beginend.grozmerbackend.entity.RoleEntity;
import com.beginend.grozmerbackend.entity.UserEntity;
import com.beginend.grozmerbackend.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private IUserDao userDao;
    private UserDto userDto = new UserDto();

    public UserService(IUserDao userDao){
        this.userDao = userDao;
    }

    public List<User> findAll(String search){
        return this.userDto.transferEntityToModelList(this.userDao.findAll(hasSearch(search)));
    }

    public User findByLogin(String login){
        return this.userDto.transferEntityToModel(this.userDao.findByLogin(login));
    }

    @Transactional
    public User save(User user){
        return this.userDto.transferEntityToModel(this.userDao.save(this.userDto.transferModelToEntity(user)));
    }

    static Specification<UserEntity> hasSearch(String search) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                if(search != null && search != ""){
                    Predicate searchNamePredicate = cb.like(cb.upper(root.get("name")), "%" + search.toUpperCase() + "%");
                    Predicate searchLoginPredicate = cb.like(cb.upper(root.get("login")), "%" + search.toUpperCase() + "%");
                    Join roleJoin = root.join("role");
                    Predicate searchRolePredicate = cb.like(cb.upper(roleJoin.get("name")), "%" + search.toUpperCase() + "%");
                    return cb.or(searchNamePredicate, searchLoginPredicate, searchRolePredicate);
                }
                return null;
            }
        };
        return specification;
    }
}
