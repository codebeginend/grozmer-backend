package com.beginend.grozmerbackend.dao;

import com.beginend.grozmerbackend.entity.PlotProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlotProgressDao extends JpaRepository<PlotProgressEntity, Long> {
}
