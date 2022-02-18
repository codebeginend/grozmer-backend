package com.beginend.grozmerbackend.service;

import com.beginend.grozmerbackend.dao.IPlotDocsDao;
import com.beginend.grozmerbackend.entity.PlotDocsEntity;
import com.beginend.grozmerbackend.service.storage.FileSystemStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlotDocsService {

    private IPlotDocsDao plotDocsDao;
    private FileSystemStorageService fileSystemStorageService;


    public PlotDocsService(IPlotDocsDao plotDocsDao, FileSystemStorageService fileSystemStorageService){
        this.plotDocsDao = plotDocsDao;
        this.fileSystemStorageService = fileSystemStorageService;
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

    public void deleteById(Long plotDocsId){
        PlotDocsEntity plotDocsEntity = this.plotDocsDao.getById(plotDocsId);
        fileSystemStorageService.delete(plotDocsEntity.getName());
        this.plotDocsDao.deleteById(plotDocsId);
    };
}
