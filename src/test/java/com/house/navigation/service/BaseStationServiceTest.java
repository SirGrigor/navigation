package com.house.navigation.service;

import com.house.navigation.domain.BaseStation;
import com.house.navigation.repository.BaseStationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
class BaseStationServiceTest {

    @Mock
    BaseStationRepository baseStationRepository;

    @InjectMocks
    BaseStationService baseStationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addBaseStationTest() {
        BaseStation baseStation = new BaseStation();
        baseStation.setName("Base");
        baseStation.setCoordinateY(2);
        baseStation.setCoordinateX(3);
        baseStation.setDetectionRadiusInMeters(4);

        Mockito.when(baseStationRepository.save(baseStation)).thenReturn(baseStation);

        Assertions.assertEquals(3, baseStation.getCoordinateX());
        Assertions.assertEquals(2, baseStation.getCoordinateY());
        Assertions.assertEquals("Base", baseStation.getName());
        Assertions.assertEquals(4, baseStation.getDetectionRadiusInMeters());
    }

    @Test
    void canAddBaseStation(){
        BaseStation baseStation = new BaseStation();
        Mockito.when(baseStationRepository.save(any(BaseStation.class))).thenReturn(baseStation);
    }
    }