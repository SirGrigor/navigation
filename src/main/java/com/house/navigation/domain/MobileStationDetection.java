package com.house.navigation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "mobile_station_detection")
public class MobileStationDetection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "mobile_station_detection_id")
    private Long id;

    @Column(name = "mobile_station_id")
    private UUID mobileStationId;

    @Column(name = "distance")
    private float distance;

    @Column(name = "timestamp")
    private Date timestamp;

    @JsonIgnore
    @Column(name = "base_station_id", columnDefinition = "BINARY(16)")
    private UUID baseStationId;

}
