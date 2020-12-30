package com.house.navigation.controller;

import com.house.navigation.domain.BaseStation;
import com.house.navigation.service.BaseStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseStationController {

    BaseStationService baseStationService;

    public BaseStationController(BaseStationService baseStationService) {
        this.baseStationService = baseStationService;
    }

    @PostMapping("/base-stations")
    public ResponseEntity<BaseStation> addBaseStation(@RequestBody BaseStation baseStation){
        baseStationService.addBaseStation(baseStation);
        return ResponseEntity.ok(baseStation);
    }
}
