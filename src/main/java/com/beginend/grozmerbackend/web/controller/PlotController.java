package com.beginend.grozmerbackend.web.controller;

import com.beginend.grozmerbackend.model.Plot;
import com.beginend.grozmerbackend.service.PlotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plot")
public class PlotController {

    private PlotService plotService;

    public PlotController(PlotService plotService){
        this.plotService = plotService;
    }

    @GetMapping("all")
    public List<Plot> getAll(@RequestParam(required = false) String search){
        return this.plotService.getAll(search);
    }

    @PostMapping
    public Plot save(@RequestBody Plot plot){
        return plotService.save(plot);
    }
}
