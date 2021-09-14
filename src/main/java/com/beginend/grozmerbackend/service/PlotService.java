package com.beginend.grozmerbackend.service;

import com.beginend.grozmerbackend.dao.IPlotDao;
import com.beginend.grozmerbackend.dao.IPlotProgressDao;
import com.beginend.grozmerbackend.dto.PlotDto;
import com.beginend.grozmerbackend.entity.PlotEntity;
import com.beginend.grozmerbackend.entity.PlotProgressEntity;
import com.beginend.grozmerbackend.entity.PlotStatusEnum;
import com.beginend.grozmerbackend.entity.UserEntity;
import com.beginend.grozmerbackend.model.Plot;
import com.beginend.grozmerbackend.model.User;
import com.beginend.grozmerbackend.web.exception.WebNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class PlotService {

    private IPlotDao plotDao;
    private PlotDto plotDto = new PlotDto();
    private UserService userService;
    private IPlotProgressDao plotProgressDao;

    public PlotService(IPlotDao plotDao, UserService userService, IPlotProgressDao plotProgressDao){
        this.plotDao = plotDao;
        this.userService = userService;
        this.plotProgressDao = plotProgressDao;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) auth.getPrincipal();
        User user1 = userService.findByLogin(user.getUsername());
        PlotProgressEntity progressEntity = new PlotProgressEntity();
        progressEntity.setUserId(user1.getId());
        if(plot.getId() == null){
            progressEntity.setStatus(PlotStatusEnum.CREATE);
        }else{
            progressEntity.setStatus(PlotStatusEnum.UPDATE);
        }
        PlotEntity plotEntity = this.plotDao.save(plotDto.transferModelToEntity(plot));
        progressEntity.setPlotId(plotEntity.getId());
        plotProgressDao.save(progressEntity);
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
