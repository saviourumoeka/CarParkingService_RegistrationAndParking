package com.KrissSaviour.CarParkingService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KrissSaviour.CarParkingService.Model.Cars;
/*
 * Data Access Layer For Cars Model*/
@Repository
public interface CarsRepository extends JpaRepository<Cars, Integer> {

	//Query to find a car with the plate number
	public Cars findByPlateNumber(String plateNumber);
}
