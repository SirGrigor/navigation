package com.house.navigation.repository;

import com.house.navigation.domain.ReportStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportStationRepository extends JpaRepository<ReportStation, Long> {
    List<ReportStation> findFirst3ByMobileStationUuidOrderByTimestampDesc(UUID mobileStationUuid);
}
