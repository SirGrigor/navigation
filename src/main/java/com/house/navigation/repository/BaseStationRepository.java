package com.house.navigation.repository;

import com.house.navigation.domain.BaseStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BaseStationRepository extends JpaRepository<BaseStation, Long> {
    BaseStation findByBaseStationUuid(UUID uuid);

    boolean existsByBaseStationUuid(UUID uuid);
}
