package com.house.navigation.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "base_station")
public class BaseStation {

    @Id
    @Column(name = "base_station_id", columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "base_station_name")
    private String name;

    @Column(name = "coordinate_x")
    private float coordinateX;

    @Column(name = "coordinate_y")
    private float coordinateY;

    @Column(name = "detection_radius_in_meters")
    private float detectionRadiusInMeters;

}
