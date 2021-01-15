package com.house.navigation.service;

import com.house.navigation.DTO.MobileStationDTO;
import com.house.navigation.DTO.ReportDTO;
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

    public void addBaseStationReport(ReportDTO reportDTO) {
        List<ReportStation> reportStations = new ArrayList<>();
        reportDTO.getReports().forEach(reportMobileStation ->
                validateReportData(reportDTO, reportStations, reportMobileStation));
        reportStationRepository.saveAll(reportStations);
    }

    public MobileStationDTO getReportForMobileStation(UUID mobileStationUuid) throws NotFoundException {
        return CalculateDistanceService.getMobileStationReport(mobileStationUuid);
    }

    private void validateReportData(ReportDTO reportDTO, List<ReportStation> reportStations,
                                    com.house.navigation.DTO.ReportDataDTO reportMobileStation) {

        ReportStation reportStation = new ReportStation();
        reportStation.setBaseStationUuid(reportDTO.getBaseStationUuid());
        reportStation.setMobileStationUuid(reportMobileStation.getMobileStationUuid());
        reportStation.setTimestamp(reportMobileStation.getTimestamp());

        if (reportMobileStation.getDistance() < baseStationRepository.findByBaseStationUuid(
                reportDTO.getBaseStationUuid()).getDetectionRadiusInMeters()) {
            reportStation.setDistance(reportMobileStation.getDistance());
            reportStations.add(reportStation);

        } else {
            log.error("Report for BaseStation: " + reportDTO.getBaseStationUuid()
                    + " has invalid distance configuration for mobile station with number: "
                    + reportMobileStation.getMobileStationUuid()
                    + " mobile Station is Out of registration Bounds");
        }
    }

    public boolean reportRepositoryNotEmpty() {
        return reportStationRepository.findById(1L).isPresent();
    }

    public boolean existByUuid(UUID baseStationUuid) {
        return baseStationRepository.existsByBaseStationUuid(baseStationUuid);
    }
}