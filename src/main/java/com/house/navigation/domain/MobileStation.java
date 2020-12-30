package com.house.navigation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "mobile_station")
public class MobileStation {

    @Id
    @Column(name = "mobile_station_id", columnDefinition = "BINARY(16)")
    @GeneratedValue
    private UUID uuid;

    @Column(name = "last_coordinate_x")
    private float lastCoordinateX;

    @Column(name = "last_coordinate_y")
    private float lastCoordinateY;

}
