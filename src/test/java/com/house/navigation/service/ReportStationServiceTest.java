package com.house.navigation.service;

import com.house.navigation.domain.MobileStationDetection;
import com.house.navigation.domain.ReportStation;
import com.house.navigation.repository.ReportStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)

class ReportStationServiceTest {

    @Mock
    ReportStationRepository reportStationRepository;

    @InjectMocks
    ReportStationService reportStationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void canAddBaseStationReport() {
        ReportStation reportStation = new ReportStation();
        Mockito.when(reportStationRepository.save(reportStation)).thenReturn(reportStation);
    }

    @Test
    void serviceCanAddBaseStationReport() {

        ReportStation reportStation = new ReportStation();
        reportStation.setBaseStationId(UUID.randomUUID());

        Set<MobileStationDetection> report = new HashSet<>();

        Timestamp ts=new Timestamp(System.currentTimeMillis());
        Date date=new Date(ts.getTime());

        MobileStationDetection mobileStationDetection = new MobileStationDetection();
        mobileStationDetection.setMobileStationId(UUID.randomUUID());
        mobileStationDetection.setDistance(5);
        mobileStationDetection.setTimestamp(date);

        report.add(mobileStationDetection);

        reportStation.setReports(report);

        Mockito.when(reportStationRepository.save(reportStation)).thenReturn(reportStation);

        assertEquals(1, reportStation.getReports().size());
        reportStation.getReports().forEach(mobileStationDetection1 -> {
            assertEquals(5, mobileStationDetection1.getDistance());
            assertEquals(date, mobileStationDetection1.getTimestamp());
        });
    }
}