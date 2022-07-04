package com.beginend.grozmerbackend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plot")
public class PlotEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String cadastralNumber;
    private String square;
    private String owner;
    private String ownerType;
    private String numberPhone;
    private double latitude;
    private double longitude;
    private boolean active;
    private boolean isSued;

    @Enumerated(EnumType.STRING)
    private PlotTypesEnum type;

    @OneToMany(mappedBy = "plot")
    private List<PlotDocsEntity> docs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public void setCadastralNumber(String cadastralNumber) {
        this.cadastralNumber = cadastralNumber;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<PlotDocsEntity> getDocs() {
        return docs;
    }

    public void setDocs(List<PlotDocsEntity> docs) {
        this.docs = docs;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public PlotTypesEnum getType() {
        return type;
    }

    public void setType(PlotTypesEnum type) {
        this.type = type;
    }

    public boolean isSued() {
        return isSued;
    }

    public void setSued(boolean sued) {
        isSued = sued;
    }
}
