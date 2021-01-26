package com.house.navigation.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileStationDto {

    private UUID mobileStationUuid;
    private float coordinateX;
    private float coordinateY;
    private float errorRadius;
    private int errorCode;
    private String errorDescription;
    private List<double []> mobileStationStackTrace;
}
