package com.house.navigation.repository;

import com.house.navigation.domain.MobileStationDetection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MobileStationDetectionRepository extends JpaRepository<MobileStationDetection, Long> {
    List<MobileStationDetection> findByMobileStationIdOrderByDistanceAsc(UUID mobileStationUuid);
    MobileStationDetection findByMobileStationId(UUID mobileStationId);
    boolean existsByMobileStationId(UUID mobileStationId);
}
