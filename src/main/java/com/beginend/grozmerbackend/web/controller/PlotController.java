package com.beginend.grozmerbackend.web.controller;

import com.beginend.grozmerbackend.model.Plot;
import com.beginend.grozmerbackend.service.PlotDocsService;
import com.beginend.grozmerbackend.service.PlotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("plot")
public class PlotController {

    private PlotService plotService;

    private PlotDocsService plotDocsService;

    public PlotController(PlotService plotService, PlotDocsService plotDocsService){
        this.plotService = plotService;
        this.plotDocsService = plotDocsService;
    }

    @GetMapping("admin/all")
    public List<Plot> getAllAdmin(@RequestParam(required = false) String search, @RequestParam(required = false) Boolean active, @RequestParam(required = false) Boolean isSued){
        List<Plot> plots = this.plotService.getAll(search);
        if(active != null){
            plots = plots.stream().filter(f -> f.isActive() == active).collect(Collectors.toList());
        }
        if(isSued != null){
            if(!isSued){
                List<Long> ids = plots.stream().filter(f -> f.isSued() == true).map(m -> m.getId()).collect(Collectors.toList());
                plots = plots.stream().filter(f -> !ids.contains(f.getId())).collect(Collectors.toList());
            }
        }
        return plots;
    }

    @GetMapping("all")
    public List<Plot> getAll(@RequestParam(required = false) String search, @RequestParam(required = false) Boolean active){
        List<Plot> plots = this.plotService.getAll(search);
        if(active != null){
            plots = plots.stream().filter(f -> f.isActive() == active && f.isSued() == false).collect(Collectors.toList());
        }
        return plots;
    }

    @PostMapping
    public Plot save(@RequestBody Plot plot){
        return plotService.save(plot);
    }

    @DeleteMapping("docs")
    private void deleteById(@RequestParam(name = "plotDocsId") Long plotDocsId){
        this.plotDocsService.deleteById(plotDocsId);
    }
}
