package com.house.navigation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "base_station")
public class BaseStation {

    @Id
    @Column(name = "base_station_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long baseStationId;

    @Column(name = "base_station_uuid")
    private UUID baseStationUuid;

    @Column(name = "base_station_name")
    private String baseStationName;

    @Column(name = "coordinate_x")
    private float coordinateX;

    @Column(name = "coordinate_y")
    private float coordinateY;

    @Column(name = "detection_radius_in_meters")
    private float detectionRadiusInMeters;

}
