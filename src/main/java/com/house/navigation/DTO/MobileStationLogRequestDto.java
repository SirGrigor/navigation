package com.house.navigation.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileStationLogRequestDto {

    private UUID mobileStationUuid;
    private Date reportStartTime;
    private Date reportEndTime;
}
