package com.house.navigation.controller;

import com.house.navigation.DTO.MobileStationDTO;
import com.house.navigation.DTO.ReportDTO;
import com.house.navigation.service.ReportStationService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class StationController {

    ReportStationService reportStationService;

    public StationController(ReportStationService reportStationService) {
        this.reportStationService = reportStationService;
    }

    @PostMapping("/location")
    public ResponseEntity<ReportDTO> addBaseStationReport(@RequestBody ReportDTO reportDTO) {
        if (!reportStationService.existByUuid(reportDTO.getBaseStationUuid())) {
            log.error("BaseStation with UUID: " + reportDTO.getBaseStationUuid() + " dont exist.");
        } else {
            reportStationService.addBaseStationReport(reportDTO);
        }
        return ResponseEntity.ok(reportDTO);
    }

    @GetMapping("/location/{mobileStationUuid}")
    public MobileStationDTO getMobileStationReport(@PathVariable UUID mobileStationUuid) throws NotFoundException {

        if (reportStationService.reportRepositoryNotEmpty()) {
            return reportStationService.getReportForMobileStation(mobileStationUuid);

        } else {
            log.error("Not enough reports to generate Calculation process.");
            throw new ArithmeticException("Add at least 1 Base Station report! Trilateration calculation cannot be proceeded");
        }
    }

}

