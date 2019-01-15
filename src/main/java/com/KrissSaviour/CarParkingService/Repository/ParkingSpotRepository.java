package com.KrissSaviour.CarParkingService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KrissSaviour.CarParkingService.Model.ParkingSpot;

/*
 * Data access Layer for Parking spot model*/
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Integer> {
	
	

}
