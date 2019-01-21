package com.KrissSaviour.CarParkingService.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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

	@Autowired
	// @PersistenceContext
	EntityManager em;

//Query to update parking spot
	public Cars updateCarParkingSpot(String plateNumber, ParkingSpot parkingSpot) {
		Cars car = carsRepo.findByPlateNumber(plateNumber);

		car.setParkingSpot(null);
		parkingSpot = em.find(ParkingSpot.class, parkingSpot.getId());

		parkingSpot.setCar(null);
		parkingSpot.setVacancy(true);

		em.merge(car);
		em.merge(parkingSpot);
		return car;
	};

	@GetMapping("/findcar/{plateNumber}")
	@Transactional
	public Cars findCar(@PathVariable("plateNumber") String plateNumber) {

		Cars cars = carsRepo.findByPlateNumber(plateNumber);

		if (cars.getParkingSpot() == null) {
			return null;
		}
		return cars;

	}
	
	@GetMapping("/getcar/{plateNumber}")
	@Transactional
	public Cars getCar(@PathVariable("plateNumber") String plateNumber) {

		Cars car = carsRepo.findByPlateNumber(plateNumber);

		if (car.getParkingSpot() == null) {
			return null;
		}
		
		ParkingSpot spot = car.getParkingSpot();
		updateCarParkingSpot(plateNumber, spot);
		return car;

	}
}
