package com.house.navigation.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class MobileStationDTO {

    private UUID mobileStationId;
    private float coordinateX;
    private float coordinateY;
    private float errorRadius;
    private int errorCode;
    private String errorDescription;

}
