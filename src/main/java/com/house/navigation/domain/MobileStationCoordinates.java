package com.house.navigation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MobileStationCoordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ms_coordinates_id")
    private Long id;

    @Column(name = "mobile_station_uuid")
    private UUID mobileStationUuid;

    @Column(name = "last_coordinate_x")
    private float coordinateX;

    @Column(name = "last_coordinate_y")
    private float coordinateY;

    @Column(name = "registation_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date registrationTime;

    public MobileStationCoordinates(UUID mobileStationUuid, float coordinateX, float coordinateY, Date registrationTime) {
        this.mobileStationUuid = mobileStationUuid;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.registrationTime = registrationTime;
    }
}
