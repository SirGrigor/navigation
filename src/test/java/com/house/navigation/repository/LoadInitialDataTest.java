package com.house.navigation.repository;

import com.house.navigation.domain.BaseStation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/data.sql")
public class LoadInitialDataTest {

    @Autowired
    BaseStationRepository baseStationRepository;

    @Test
    public void checkBaseStationEntitiesInit(){
        List<BaseStation> baseStationList = baseStationRepository.findAll();
        assertEquals(9, baseStationList.size());
        assertEquals("Base1", baseStationList.get(0).getBaseStationName());
        assertEquals(5.0, baseStationList.get(0).getCoordinateX(), 0.001);
        assertEquals(0.0, baseStationList.get(0).getCoordinateY(), 0.001);
        assertEquals(10.0, baseStationList.get(0).getDetectionRadiusInMeters(), 0.001);
        Assert.assertTrue(baseStationRepository.existsById(8L));
    }
}
