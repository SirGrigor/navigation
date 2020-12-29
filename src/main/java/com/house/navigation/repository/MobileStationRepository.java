package com.house.navigation.repository;

import com.house.navigation.domain.MobileStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MobileStationRepository extends JpaRepository<MobileStation, UUID> {
}
