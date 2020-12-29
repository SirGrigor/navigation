package com.house.navigation.controller;

import com.house.navigation.DTO.MobileStationDTO;
import com.house.navigation.domain.ReportStation;
import com.house.navigation.service.MobileStationService;
import com.house.navigation.service.ReportStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@Slf4j
public class StationController {

    ReportStationService reportStationService;
    MobileStationService mobileStationService;


    public StationController(ReportStationService reportStationService, MobileStationService mobileStationService) {
        this.reportStationService = reportStationService;
        this.mobileStationService = mobileStationService;
    }



    @PostMapping("/locations")
    public ResponseEntity<ReportStation> addBaseStationReport(@RequestBody ReportStation reportStation){
        log.info("baseStationId: " + reportStation.getBaseStationId()
                + "Reports: " + reportStation.getReports());
        reportStationService.addBaseStationReport(reportStation);
        return ResponseEntity.ok(reportStation);
    }

    @GetMapping("/locations/{uuid}")
    public MobileStationDTO getMobileStationReport(@PathVariable UUID uuid){
        return mobileStationService.getReport(uuid);
    }
}

