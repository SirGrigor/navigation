package com.house.navigation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mobile_station")
public class MobileStation {

    @Id
    @Column(name = "mobile_station_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mobileStationId;

    @Column(name = "mobile_station_uuid")
    private UUID mobileStationUuid;

    @Column(name = "last_coordinate_x")
    private float lastCoordinateX;

    @Column(name = "last_coordinate_y")
    private float lastCoordinateY;

    @OneToMany(mappedBy = "mobileStation")
    private Set<MobileStationCoordinates> mobileStationCoordinates;

    public MobileStation(UUID mobileStationUuid, float lastCoordinateX, float lastCoordinateY) {
        this.mobileStationUuid = mobileStationUuid;
        this.lastCoordinateX = lastCoordinateX;
        this.lastCoordinateY = lastCoordinateY;
    }
}
