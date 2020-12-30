package com.house.navigation.service;

import com.house.navigation.domain.MobileStationDetection;
import com.house.navigation.domain.ReportStation;
import com.house.navigation.repository.MobileStationDetectionRepository;
import com.house.navigation.repository.ReportStationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportStationService {

    ReportStationRepository reportRepository;
    MobileStationDetectionRepository mobileStationDetectionRepository;

    public ReportStationService(ReportStationRepository reportRepository,
                                MobileStationDetectionRepository mobileStationDetectionRepository) {
        this.reportRepository = reportRepository;
        this.mobileStationDetectionRepository = mobileStationDetectionRepository;
    }

    public void addBaseStationReport(ReportStation reportStation){
        if(!reportRepository.existsById(reportStation.getBaseStationId())) {
            reportRepository.save(reportStation);

    } else {
            reportStation.getReports().forEach(report -> {
                if(mobileStationDetectionRepository.existsByMobileStationId(report.getMobileStationId())){
                    MobileStationDetection detectionReport = mobileStationDetectionRepository.
                            findByMobileStationId(report.getMobileStationId());
                    detectionReport.setDistance(report.getDistance());
                    detectionReport.setTimestamp(report.getTimestamp());

                    mobileStationDetectionRepository.save(detectionReport);
                }
                else {
                    report.setBaseStationId(reportStation.getBaseStationId());
                    mobileStationDetectionRepository.save(report);
                }
            });

        }
}}
