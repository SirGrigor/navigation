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
    @Column(name = "mobile_station_id_coordinates")
    private Long id;
    private UUID mobileStationUuid;
    private float coordinateX;
    private float coordinateY;
    private Date registrationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mobile_station_id")
    private MobileStation mobileStation;
}
