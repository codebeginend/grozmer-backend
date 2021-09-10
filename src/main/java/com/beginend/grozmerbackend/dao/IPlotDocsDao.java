package com.beginend.grozmerbackend.dao;

import com.beginend.grozmerbackend.entity.PlotDocsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlotDocsDao extends JpaRepository<PlotDocsEntity, Long> {

    List<PlotDocsEntity> findAllByPlotId(Long plotId);
}
