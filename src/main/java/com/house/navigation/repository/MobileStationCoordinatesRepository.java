package com.house.navigation.repository;

import com.house.navigation.domain.MobileStationCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileStationCoordinatesRepository extends JpaRepository<MobileStationCoordinates, Long> {
}
