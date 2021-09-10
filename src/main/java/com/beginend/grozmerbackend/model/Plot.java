package com.beginend.grozmerbackend.model;

import lombok.Data;

@Data
public class Plot {
    private Long id;
    private String address;
    private String cadastralNumber;
    private double square;
    private String owner;
    private String ownerType;
    private String numberPhone;
    private double latitude;
    private double longitude;
}
