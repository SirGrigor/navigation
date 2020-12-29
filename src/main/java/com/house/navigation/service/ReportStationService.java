package com.house.navigation.service;

import com.house.navigation.domain.ReportStation;
import com.house.navigation.repository.ReportStationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportStationService {

    ReportStationRepository repository;

    public ReportStationService(ReportStationRepository repository) {
        this.repository = repository;
    }

    public void addBaseStationReport(ReportStation reportStation){
        repository.save(reportStation);
    }

}
