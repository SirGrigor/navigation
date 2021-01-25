package com.house.navigation.service;

import com.house.navigation.DTO.MobileStationDto;
import com.house.navigation.DTO.MobileStationLogRequestDto;
import com.house.navigation.domain.BaseStation;
import com.house.navigation.domain.MobileStation;
import com.house.navigation.domain.ReportStation;
import com.house.navigation.repository.BaseStationRepository;
import com.house.navigation.repository.MobileStationRepository;
import com.house.navigation.repository.ReportStationRepository;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CalculateDistanceService {
    private final BaseStationRepository baseStationRepository;
    private final ReportStationRepository reportStationRepository;
    private final MobileStationRepository mobileStationRepository;

    public CalculateDistanceService(BaseStationRepository baseStationRepository, ReportStationRepository reportStationRepository, MobileStationRepository mobileStationRepository) {
        this.baseStationRepository = baseStationRepository;
        this.reportStationRepository = reportStationRepository;
        this.mobileStationRepository = mobileStationRepository;
    }

    public MobileStationDto getMobileStationReport(MobileStationLogRequestDto mobileStationLogRequestDto) throws NotFoundException {
        List<ReportStation> reportStations = getSortedByTimeStampReportStations(mobileStationLogRequestDto.getMobileStationUuid());
        List<BaseStation> baseStations = getExtractedBaseStationsForCalculation(reportStations);

        LeastSquaresOptimizer.Optimum optimum = calculateMobileStationPosition(reportStations, baseStations);
        double[] mobileStationCoordinates = optimum.getPoint().toArray();
        return getMobileStationDTO(mobileStationLogRequestDto.getMobileStationUuid(), reportStations, calculateErrorRadius(mobileStationCoordinates, baseStations.get(0)), mobileStationCoordinates);
    }

    public MobileStationDto getMobileStationDTO(UUID mobileStationUuid, List<ReportStation> reportStations, float errorRadius, double[] mobileStationCoordinates) {
        saveLastMobileStationCoordinates(mobileStationUuid, mobileStationCoordinates);

        float mobileStationCoordinateX = (float) mobileStationCoordinates[0];
        float mobileStationCoordinateY = (float) mobileStationCoordinates[1];

        if (reportStations.size() < 3) {
            String errorMessage = "Number of Stations is less than 3, please wait for additional Base Report";
            return new MobileStationDto(mobileStationUuid, mobileStationCoordinateX, mobileStationCoordinateY, errorRadius, 301, errorMessage);

        } else {
            String errorMessage = "3 Base Station Provided report. Calculation process is healthy";
            return new MobileStationDto(mobileStationUuid, mobileStationCoordinateX, mobileStationCoordinateY, errorRadius, 200, errorMessage);
        }
    }

    public void saveLastMobileStationCoordinates(UUID mobileStationUuid, double[] mobileStationCoordinates) {
        MobileStation mobileStation = new MobileStation(mobileStationUuid, (float) mobileStationCoordinates[0], (float) mobileStationCoordinates[1]);
        mobileStationRepository.save(mobileStation);
    }

    public float calculateErrorRadius(double[] mobileStationCoordinates, BaseStation baseStation) {
        float coordinateX = (float) Math.pow(baseStation.getCoordinateX() - mobileStationCoordinates[0], 2);
        float coordinateY = (float) Math.pow(baseStation.getCoordinateY() - mobileStationCoordinates[1], 2);
        float vectorLen = (float) Math.pow((coordinateX + coordinateY), 0.5);
        return (float) Math.pow(baseStation.getDetectionRadiusInMeters() - vectorLen, 0.5);
    }


    public LeastSquaresOptimizer.Optimum calculateMobileStationPosition(List<ReportStation> reportStations, List<BaseStation> baseStations) {
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
                new TrilaterationFunction(getBaseStationPositions(baseStations), getDistanceUntilMobileStation(reportStations)),
                new LevenbergMarquardtOptimizer());
        return solver.solve();
    }

    public double[][] getBaseStationPositions(List<BaseStation> getBaseStations) {
        int counter = 0;
        int numberOfBaseStationsToExtract = 3;
        int axisPositions = 2;
        double[][] baseStationPositions = new double[numberOfBaseStationsToExtract][axisPositions];
        for (BaseStation baseStation : getBaseStations) {
            double[] axisPos = new double[]{baseStation.getCoordinateX(), baseStation.getCoordinateY()};
            baseStationPositions[counter++] = axisPos;
        }
        return baseStationPositions;
    }

    public double[] getDistanceUntilMobileStation(List<ReportStation> getReports) {
        int numberOfPositionsToCalculate = 3;
        double[] distances = new double[numberOfPositionsToCalculate];
        int counter = 0;
        for (ReportStation reportStation : getReports) {
            distances[counter++] = reportStation.getDistance();
        }
        return distances;
    }

    public List<BaseStation> getExtractedBaseStationsForCalculation(List<ReportStation> getReports) {
        List<BaseStation> getBaseStations = new ArrayList<>();
        getReports.forEach(reportStation -> getBaseStations.add(baseStationRepository.findByBaseStationUuid(reportStation.getBaseStationUuid())));
        return getBaseStations;
    }

    public List<ReportStation> getSortedByTimeStampReportStations(UUID mobileStationUuid) throws NotFoundException {
        if (reportStationRepository.findFirst3ByMobileStationUuidOrderByTimestampDesc(mobileStationUuid).size() == 0) {
            log.error("Mobile Station with UUID: " + mobileStationUuid + " is not in our Data Base.");
            throw new NotFoundException("Mobile Station with UUID: " + mobileStationUuid + " is not in our Data Base.");
        }
        return reportStationRepository.findFirst3ByMobileStationUuidOrderByTimestampDesc(mobileStationUuid);
    }
}
