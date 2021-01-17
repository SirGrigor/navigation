package com.house.navigation.service;

import com.house.navigation.DTO.MobileStationDto;
import com.house.navigation.DTO.ReportDto;
import com.house.navigation.DTO.ReportDtoMobileStationRecords;
import com.house.navigation.domain.ReportStation;
import com.house.navigation.repository.BaseStationRepository;
import com.house.navigation.repository.ReportStationRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ReportStationService {

    private final ReportStationRepository reportStationRepository;
    private final BaseStationRepository baseStationRepository;
    private final CalculateDistanceService CalculateDistanceService;

    public ReportStationService(ReportStationRepository reportStationRepository, BaseStationRepository baseStationRepository, com.house.navigation.service.CalculateDistanceService calculateDistanceService) {
        this.reportStationRepository = reportStationRepository;
        this.baseStationRepository = baseStationRepository;
        CalculateDistanceService = calculateDistanceService;
    }

    public void addBaseStationReport(ReportDto reportDTO) {
        List<ReportStation> reportStations = new ArrayList<>();
        reportDTO.getReports().forEach(reportMobileStation ->
                validateReportData(reportDTO, reportStations, reportMobileStation));
        reportStationRepository.saveAll(reportStations);
    }

    public MobileStationDto getReportForMobileStation(UUID mobileStationUuid) throws NotFoundException {
        return CalculateDistanceService.getMobileStationReport(mobileStationUuid);
    }

    private void validateReportData(ReportDto reportDTO, List<ReportStation> reportStations,
                                    ReportDtoMobileStationRecords reportMobileStation) {
        if (reportMobileStation.getDistance() < baseStationRepository.findByBaseStationUuid(
                reportDTO.getBaseStationUuid()).getDetectionRadiusInMeters()) {
            ReportStation reportStation = createReport(reportDTO, reportMobileStation);
            reportStations.add(reportStation);

        } else {
            log.error("Report for BaseStation: " + reportDTO.getBaseStationUuid()
                    + " has invalid distance configuration for mobile station with number: "
                    + reportMobileStation.getMobileStationUuid()
                    + " mobile Station is Out of registration Bounds");
        }
    }

    private ReportStation createReport(ReportDto reportDTO, ReportDtoMobileStationRecords reportMobileStation) {
        return new ReportStation(reportDTO.getBaseStationUuid(), reportMobileStation.getMobileStationUuid(), reportMobileStation.getDistance(), reportMobileStation.getTimestamp());
    }

    public boolean reportRepositoryNotEmpty() {
        return reportStationRepository.findById(1L).isPresent();
    }

    public boolean existByUuid(UUID baseStationUuid) {
        return baseStationRepository.existsByBaseStationUuid(baseStationUuid);
    }
}