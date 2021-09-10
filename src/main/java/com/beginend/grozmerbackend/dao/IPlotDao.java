package com.beginend.grozmerbackend.dao;

import com.beginend.grozmerbackend.entity.PlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlotDao extends JpaRepository<PlotEntity, Long>, JpaSpecificationExecutor<PlotEntity> {
}
