package com.house.navigation;

import com.house.navigation.domain.BaseStation;
import com.house.navigation.repository.BaseStationRepository;
import com.house.navigation.repository.ReportStationRepository;
import com.house.navigation.service.ReportStationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/data.sql")
class NavigationApplicationInitTest {

    @Autowired
    BaseStationRepository baseStationRepository;

    @Autowired
    ReportStationRepository reportStationRepository;

    @Autowired
    ReportStationService reportStationService;

    @Test
    public void checkBaseStationEntitiesInit() {
        List<BaseStation> baseStationList = baseStationRepository.findAll();
        Assertions.assertEquals(9, baseStationList.size());
        Assertions.assertEquals("Base1", baseStationList.get(0).getBaseStationName());
        Assertions.assertEquals(5.0, baseStationList.get(0).getCoordinateX(), 0.001);
        Assertions.assertEquals(0.0, baseStationList.get(0).getCoordinateY(), 0.001);
        Assertions.assertEquals(10.0, baseStationList.get(0).getDetectionRadiusInMeters(), 0.001);
        Assertions.assertTrue(baseStationRepository.existsById(8L));
    }

//    @Test
//    void addBaseStationReport() {
//        Date date = new Date();
//        Set<ReportDtoMobileStationRecords> msRecordsSet = new HashSet<>();
//        System.out.println(baseStationRepository.findAll().size());
//        UUID uuid = baseStationRepository.findAll().get(0).getBaseStationUuid();
//        System.out.println(uuid);
//        ReportDtoMobileStationRecords msRecords = new ReportDtoMobileStationRecords(UUID.randomUUID(), 5, date);
//        msRecordsSet.add(msRecords);
//
//        ReportDto reportDto = new ReportDto(UUID.randomUUID(),msRecordsSet);
//        reportStationService.addBaseStationReport(reportDto);
//
//        assertThat(1).isEqualTo(reportStationRepository.findAll().size());
//    }

    @Test
    void saveLastMobileStationCoordinates() {
    }

    @Test
    void calculateErrorRadius() {
    }

    @Test
    void getExtractedBaseStationsForCalculation() {
    }

    @Test
    void getSortedByTimeStampReportStations() {
    }

    @Test
    void getMobileStationReport() {
    }

    @Test
    void contextLoads() {
    }
}
