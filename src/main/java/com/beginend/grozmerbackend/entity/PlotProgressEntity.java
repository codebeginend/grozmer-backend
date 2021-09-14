package com.beginend.grozmerbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "plot_progress")
public class PlotProgressEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private PlotEntity plot;

    @Column(name = "plot_id", insertable = true, updatable = true)
    private Long plotId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "user_id", insertable = true, updatable = true)
    private Long userId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PlotStatusEnum status;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PlotStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PlotStatusEnum status) {
        this.status = status;
    }
}
