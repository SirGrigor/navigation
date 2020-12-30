package com.house.navigation.service;

import com.house.navigation.domain.MobileStation;
import com.house.navigation.repository.BaseStationRepository;
import com.house.navigation.repository.MobileStationDetectionRepository;
import com.house.navigation.repository.MobileStationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
class MobileStationServiceTest {
    @Mock
    MobileStationDetectionRepository mobileStationDetectionRepository;

    @Mock
    MobileStationRepository mobileStationRepository;

    @Mock
    BaseStationRepository baseStationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getReport() {
    }

    @Test
    void getPositions() {
    }

    @Test
    void getDistances() {

    }

    @Test
    void canAddMobileStation() {
        MobileStation mobileStation = new MobileStation();
        Mockito.when(mobileStationRepository.save(any(MobileStation.class))).thenReturn(mobileStation);
    }

    @Test
    void canAddMobileStationCoordinates() {
        MobileStation mobileStation = new MobileStation();

        mobileStation.setLastCoordinateY(2);
        mobileStation.setLastCoordinateX(3);

        Mockito.when(mobileStationRepository.save(mobileStation)).thenReturn(mobileStation);

        Assertions.assertEquals(3, mobileStation.getLastCoordinateX());
        Assertions.assertEquals(2, mobileStation.getLastCoordinateY());
    }
}