package com.beginend.grozmerbackend.service;

import com.beginend.grozmerbackend.dao.IPlotDao;
import com.beginend.grozmerbackend.dto.PlotDto;
import com.beginend.grozmerbackend.entity.PlotEntity;
import com.beginend.grozmerbackend.entity.UserEntity;
import com.beginend.grozmerbackend.model.Plot;
import com.beginend.grozmerbackend.web.exception.WebNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class PlotService {

    private IPlotDao plotDao;
    private PlotDto plotDto = new PlotDto();

    public PlotService(IPlotDao plotDao){
        this.plotDao = plotDao;
    }

    public List<Plot> getAll(String search){
        List<Plot> list;
        try {
            list = this.plotDto.transferEntityToModelList(this.plotDao.findAll(hasSearch(search)));
        }catch (NullPointerException e){
            throw new WebNotFoundException("Список объектов пуст");
        }
        return list;
    }

    public Plot save(Plot plot){
        PlotEntity plotEntity = this.plotDao.save(plotDto.transferModelToEntity(plot));
        return this.plotDto.transferEntityToModel(plotEntity);
    }

    static Specification<PlotEntity> hasSearch(String search) {
        Specification specification = new Specification() {

            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                if(search != null && search != ""){
                    Predicate searchCadastralNumberPredicate = cb.like(cb.upper(root.get("cadastralNumber")), "%" + search.toUpperCase() + "%");
                    Predicate searchSquarePredicate = cb.like(cb.upper(root.get("square").as(String.class)), "%" + search.toUpperCase() + "%");
                    Predicate searchOwnerPredicate = cb.like(cb.upper(root.get("owner")), "%" + search.toUpperCase() + "%");
                    Predicate searchOwnerTypePredicate = cb.like(cb.upper(root.get("ownerType")), "%" + search.toUpperCase() + "%");
                    Predicate searchNumberPhonePredicate = cb.like(cb.upper(root.get("numberPhone")), "%" + search.toUpperCase() + "%");
                    Predicate searchLatitudePredicate = cb.like(cb.upper(root.get("latitude").as(String.class)), "%" + search.toUpperCase() + "%");
                    Predicate searchLongitudePredicate = cb.like(cb.upper(root.get("longitude").as(String.class)), "%" + search.toUpperCase() + "%");
                    return cb.or(searchCadastralNumberPredicate, searchSquarePredicate, searchOwnerPredicate, searchOwnerTypePredicate, searchNumberPhonePredicate, searchLatitudePredicate, searchLongitudePredicate);
                }
                return null;
            }
        };
        return specification;
    }
}
