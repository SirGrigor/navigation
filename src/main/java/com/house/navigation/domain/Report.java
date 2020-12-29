package com.house.navigation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "detection_report")
public class Report {

    @Id
    @GeneratedValue
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "base_station_id")
    private UUID baseStationId;

    @Column(name = "mobile_station_id")
    private UUID mobileStationId;

    @Column(name = "distance")
    private float distance;

    @Column(name = "timestamp")
    private Date timestamp;

}
