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
@Table(name = "detection_report")
public class ReportStation {

    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;

    @Column(name = "base_station_uuid")
    private UUID baseStationUuid;

    @Column(name = "mobile_station_uuid")
    private UUID mobileStationUuid;

    @Column(name = "distance")
    private float distance;

    @Column(name = "timestamp")
    private Date timestamp;

    public ReportStation(UUID baseStationUuid, UUID mobileStationUuid, float distance, Date timestamp) {
        this.baseStationUuid = baseStationUuid;
        this.mobileStationUuid = mobileStationUuid;
        this.distance = distance;
        this.timestamp = timestamp;
    }
}
