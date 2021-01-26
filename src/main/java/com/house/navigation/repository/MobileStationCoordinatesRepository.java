package com.house.navigation.repository;

import com.house.navigation.domain.MobileStationCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MobileStationCoordinatesRepository extends JpaRepository<MobileStationCoordinates, Long> {
    List<MobileStationCoordinates> findAllByRegistrationTimeAfterAndRegistrationTimeBefore(Date startDate, Date finishDate);
}
