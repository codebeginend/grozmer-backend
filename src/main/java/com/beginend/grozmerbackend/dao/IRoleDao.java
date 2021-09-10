package com.beginend.grozmerbackend.dao;

import com.beginend.grozmerbackend.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends JpaRepository<RoleEntity, Long> {
}
