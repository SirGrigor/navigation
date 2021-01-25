package com.house.navigation.domain;

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
    private Date registrationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mobile_station_id")
    private MobileStation mobileStation;
}
