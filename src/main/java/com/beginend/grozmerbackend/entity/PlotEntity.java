package com.beginend.grozmerbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "plot")
public class PlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String cadastralNumber;
    private double square;
    private String owner;
    private String ownerType;
    private String numberPhone;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "plot")
    private List<PlotDocsEntity> docs;
}
