package com.house.navigation.service;

import com.house.navigation.domain.BaseStation;
import com.house.navigation.repository.BaseStationRepository;
import org.springframework.stereotype.Service;

@Service
public class BaseStationService {

    BaseStationRepository baseStationRepository;

    public BaseStationService(BaseStationRepository baseStationRepository) {
        this.baseStationRepository = baseStationRepository;
    }

    public void addBaseStation(BaseStation baseStation){
        baseStationRepository.save(baseStation);
    }
}
