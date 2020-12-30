package com.house.navigation.service;

import com.house.navigation.DTO.MobileStationDTO;
import com.house.navigation.domain.MobileStation;
import com.house.navigation.domain.MobileStationDetection;
import com.house.navigation.repository.BaseStationRepository;
import com.house.navigation.repository.MobileStationDetectionRepository;
import com.house.navigation.repository.MobileStationRepository;
import com.house.navigation.util.CalculateDistance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MobileStationService {

    MobileStationDetectionRepository mobileStationDetectionRepository;
    MobileStationRepository mobileStationRepository;
    BaseStationRepository baseStationRepository;


    public MobileStationService(MobileStationDetectionRepository mobileStationDetectionRepository, MobileStationRepository mobileStationRepository, BaseStationRepository baseStationRepository) {
        this.mobileStationDetectionRepository = mobileStationDetectionRepository;
        this.mobileStationRepository = mobileStationRepository;
        this.baseStationRepository = baseStationRepository;
    }

    public MobileStationDTO getReport(UUID mobileStationId) {
        List<MobileStationDetection> detections = mobileStationDetectionRepository
                .findByMobileStationIdOrderByDistanceAsc(mobileStationId);


        CalculateDistance calculateDistance = new CalculateDistance();
        MobileStationDTO mobileStationDTO = calculateDistance
                .getReport(getPositions(detections), getDistances(detections), mobileStationId);


        addLastMobileStationCoordinates(mobileStationDTO
                .getCoordinateX(), mobileStationDTO.getCoordinateY(), mobileStationId);

        return mobileStationDTO;
    }

    private double[][] getPositions(List<MobileStationDetection> detections) {
        List<double[]> cachePositions = new ArrayList<>();

        for (MobileStationDetection detection : detections) {
            double coordinateX = baseStationRepository.getOne(detection.getBaseStationId()).getCoordinateX();
            double coordinateY = baseStationRepository.getOne(detection.getBaseStationId()).getCoordinateY();
            double[] coordinates = {coordinateX, coordinateY};
            cachePositions.add(coordinates);
        }

        double[][] positions = new double[cachePositions.size()][2];
        return cachePositions.toArray(positions);
    }

    private double[] getDistances(List<MobileStationDetection> detections) {
        double[] distance = new double[detections.size()];

        for (int i = 0; i < distance.length; i++) {
            distance[i] = detections.get(i).getDistance();
        }
        return distance;
    }

    private void addLastMobileStationCoordinates(float lastCoordinateX, float lastCoordinateY, UUID mobileStationId) {
        MobileStation mobileStation = new MobileStation();
        mobileStation.setUuid(mobileStationId);
        mobileStation.setLastCoordinateX(lastCoordinateX);
        mobileStation.setLastCoordinateY(lastCoordinateY);

        mobileStationRepository.save(mobileStation);
    }
}