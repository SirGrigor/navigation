package com.house.navigation.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "mobile_station")
public class MobileStation {

    @Id
    @Column(name = "mobile_station_id", columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "last_coordinate_x")
    private float lastCoordinateX;

    @Column(name = "last_coordinate_y")
    private float lastCoordinateY;
}
