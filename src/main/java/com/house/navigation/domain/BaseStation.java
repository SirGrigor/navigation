package com.house.navigation.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
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
}
