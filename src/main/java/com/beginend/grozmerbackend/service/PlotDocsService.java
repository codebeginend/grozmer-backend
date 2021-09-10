package com.beginend.grozmerbackend.service;

import com.beginend.grozmerbackend.dao.IPlotDocsDao;
import com.beginend.grozmerbackend.entity.PlotDocsEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlotDocsService {

    private IPlotDocsDao plotDocsDao;


    public PlotDocsService(IPlotDocsDao plotDocsDao){
        this.plotDocsDao = plotDocsDao;
    }

    @Transactional
    public PlotDocsEntity findById(Long id){
        return this.plotDocsDao.findById(id).orElse(null);
    }


    public PlotDocsEntity save(PlotDocsEntity plotDocsEntity){
        return this.plotDocsDao.save(plotDocsEntity);
    }

    public List<PlotDocsEntity> findAllByPlotId(Long plotId){
        return this.plotDocsDao.findAllByPlotId(plotId);
    }
}
