package com.beginend.grozmerbackend.dto;

import com.beginend.grozmerbackend.entity.PlotEntity;
import com.beginend.grozmerbackend.model.Plot;

import java.util.List;
import java.util.stream.Collectors;

public class PlotDto {

    public List<Plot> transferEntityToModelList(List<PlotEntity> entities){
        return entities.stream().map(entity-> transferEntityToModel(entity)).collect(Collectors.toList());
    }

    public Plot transferEntityToModel(PlotEntity entity){
            Plot plot = new Plot();
            plot.setId(entity.getId());
            plot.setCadastralNumber(entity.getCadastralNumber());
            plot.setAddress(entity.getAddress());
            plot.setSquare(entity.getSquare());
            plot.setLatitude(entity.getLatitude());
            plot.setLongitude(entity.getLongitude());
            plot.setOwner(entity.getOwner());
            plot.setOwnerType(entity.getOwnerType());
            plot.setNumberPhone(entity.getNumberPhone());
            plot.setActive(entity.isActive());
            return plot;
    }

    public PlotEntity transferModelToEntity(Plot model){
        PlotEntity entity = new PlotEntity();
        entity.setId(model.getId());
        entity.setCadastralNumber(model.getCadastralNumber());
        entity.setAddress(model.getAddress());
        entity.setSquare(model.getSquare());
        entity.setLatitude(model.getLatitude());
        entity.setLongitude(model.getLongitude());
        entity.setOwner(model.getOwner());
        entity.setOwnerType(model.getOwnerType());
        entity.setNumberPhone(model.getNumberPhone());
        entity.setActive(model.isActive());
        return entity;
    }
}
