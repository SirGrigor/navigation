package com.house.navigation.service;

import com.house.navigation.domain.ReportStation;
import com.house.navigation.repository.BaseStationRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReportStationServiceTest {

    @InjectMocks
    private ReportStationService reportStationService;

    @Mock
    BaseStationRepository baseStationRepository;

    @Test
    void addBaseStationReport() {
        Date date = new Date();
        ReportStation reportStation = new ReportStation(UUID.randomUUID(),UUID.randomUUID(),3,date);

    }

    @Test
    void getReportForMobileStation() {
    }

    @Test
    void reportRepositoryNotEmpty() {
    }

    @Test
    void existByUuid() {

    }
}