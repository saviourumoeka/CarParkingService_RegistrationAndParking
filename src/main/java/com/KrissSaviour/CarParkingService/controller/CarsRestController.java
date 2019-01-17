package com.KrissSaviour.CarParkingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.KrissSaviour.CarParkingService.Model.Cars;
import com.KrissSaviour.CarParkingService.Model.ParkingSpot;
import com.KrissSaviour.CarParkingService.Repository.CarsRepository;

@RestController
public class CarsRestController {
 @Autowired
 private CarsRepository carsRepo;
 
 @GetMapping("/getcar/{plateNumber}")
 public ParkingSpot findCar(@PathVariable("plateNumber") String plateNumber) {
	
	Cars cars =carsRepo.findByPlateNumber(plateNumber);
	
		
		 if(cars.getParkingSpot() ==null){ 
			 return null;
		 } return cars.getParkingSpot();
		
 }
}
