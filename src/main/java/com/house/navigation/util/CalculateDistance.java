package com.house.navigation.util;

import com.house.navigation.DTO.MobileStationDTO;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import java.util.UUID;


public class CalculateDistance {
    public CalculateDistance() {
    }

    private double[] calculationResponse(double[][] positions, double[] distances) throws RuntimeException{

        try {
            NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances),
                    new LevenbergMarquardtOptimizer());
            LeastSquaresOptimizer.Optimum optimum = solver.solve();

            return optimum.getPoint().toArray();

        } catch (Exception e) {
            e.printStackTrace();
            if (positions.length == 1) {
                return new double[]{positions[0][0], positions[0][1]};
            } else {

                throw new ArithmeticException("Please provide at least one BaseStation Report");
            }
        }
    }

    public MobileStationDTO getReport(double[][] positions, double[] distances, UUID mobileStationId) {
        double[] mobileStationCoordinates = calculationResponse(positions, distances);

            MobileStationDTO mobileStationDTO = new MobileStationDTO();
            mobileStationDTO.setMobileStationId(mobileStationId);
            mobileStationDTO.setCoordinateX((float) mobileStationCoordinates[0]);
            mobileStationDTO.setCoordinateY((float) mobileStationCoordinates[1]);

            if (positions.length == 1) {
                mobileStationDTO.setErrorRadius((float) distances[0]);
                mobileStationDTO.setErrorCode(300);
                mobileStationDTO.setErrorDescription("Required additional Base Station for precise calculation");
            } else {
                mobileStationDTO.setErrorRadius(errorRadius(positions, distances));
                mobileStationDTO.setErrorCode(200);
                mobileStationDTO.setErrorDescription("No error");
            }

            return mobileStationDTO;
        }

    private float errorRadius(double[][] baseStationCoordinates, double[] distance){
        float coordinateX = (float)Math.pow(baseStationCoordinates[0][0] - (float) baseStationCoordinates[1][0], 2);
        float coordinateY = (float)Math.pow(baseStationCoordinates[0][1] - (float) baseStationCoordinates[1][1], 2);
        float vectorLength = (float) Math.pow((coordinateX + coordinateY), 0.5);

        float distanceSum = (float) (distance[0] + distance[1]);
        return distanceSum - vectorLength;
    }
}
