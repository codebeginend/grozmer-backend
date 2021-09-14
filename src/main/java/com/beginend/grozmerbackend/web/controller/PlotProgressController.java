package com.beginend.grozmerbackend.web.controller;

import com.beginend.grozmerbackend.dao.IPlotProgressDao;
import com.beginend.grozmerbackend.entity.PlotProgressEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("plot/progress")
public class PlotProgressController {

    private IPlotProgressDao plotProgressDao;

    public PlotProgressController(IPlotProgressDao plotProgressDao){
        this.plotProgressDao = plotProgressDao;
    }

    @GetMapping("all")
    public List<PlotProgressEntity> findAll(){
        return  this.plotProgressDao.findAll();
    }
}
