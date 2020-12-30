package com.house.navigation.repository;

import com.house.navigation.domain.ReportStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportStationRepository extends JpaRepository<ReportStation, UUID> {
}
