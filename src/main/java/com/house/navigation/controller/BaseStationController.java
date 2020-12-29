package com.house.navigation.controller;

import com.house.navigation.domain.BaseStation;
import com.house.navigation.service.BaseStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BaseStationController {

    BaseStationService baseStationService;

    public BaseStationController(BaseStationService baseStationService) {
        this.baseStationService = baseStationService;
    }

    @PostMapping("/base-stations")
    public ResponseEntity<BaseStation> addBaseStation(@RequestBody BaseStation baseStation){
        log.info("baseStationId: " + baseStation.getUuid() + " "
        + "name" + baseStation.getName());
        baseStationService.addBaseStation(baseStation);
        return ResponseEntity.ok(baseStation);
    }
}
