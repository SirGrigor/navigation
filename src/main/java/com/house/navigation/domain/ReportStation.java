package com.house.navigation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "detection_report")
public class ReportStation {

    @Id
    @Column(name = "base_station_id", columnDefinition = "BINARY(16)")
    private UUID baseStationId;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="base_station_id")
    @OrderBy
    private Set<MobileStationDetection> reports;

}
