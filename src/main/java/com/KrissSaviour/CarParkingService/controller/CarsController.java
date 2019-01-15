package com.KrissSaviour.CarParkingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import javax.persistence.EntityManager;
import com.KrissSaviour.CarParkingService.Model.Cars;
import com.KrissSaviour.CarParkingService.Model.ParkingSpot;
import com.KrissSaviour.CarParkingService.Repository.CarsRepository;
//import com.KrissSaviour.CarParkingService.Repository.ParkingSpotRepository;

/*
 * Cars Controller*/

@Controller
@RequestMapping("/Cars")
public class CarsController {
	@Autowired
	private CarsRepository carsRepo;
	//private ParkingSpotRepository spotRepo;
	
	@Autowired
	//@PersistenceContext
	EntityManager em;

	// Query to find one free spot
	public ParkingSpot findOneVacancy(int limit) {
		return em.createQuery("Select p FROM ParkingSpot p WHERE p.vacancy=true", ParkingSpot.class)
				.setMaxResults(limit).getSingleResult();
	};

	//Returns all cars
	@GetMapping
	public List<Cars> getAllCars() {

		return carsRepo.findAll();
	}
	
	//loads Index page
	@RequestMapping("/")
	public String Index(ModelMap model) {
		return"index";
	}
//Parking car in a spot
	@RequestMapping("/parkCar")
	public String getCarByPlateNumber(@RequestParam("plateNumber") String plateNumber, ModelMap model) {
		Cars car = carsRepo.findByPlateNumber(plateNumber);
		System.out.println("Gotten Car " + car);
		if (car == null) {
			Cars cars = new Cars();
			ParkingSpot spot = findOneVacancy(1);
			System.out.println(spot);
			cars.setParkingSpot(spot);
			cars.setPlateNumber(plateNumber);
			spot.setCar(cars);
			spot.setVacancy(false);
			carsRepo.save(cars);
			model.addAttribute("msg", "Car with plate number" + plateNumber + " parked at " + spot.getParkingSpot());
			return "index";
		}

		ParkingSpot spot = findOneVacancy(1);
		System.out.println("found Spot "+spot);
	
		spot.setCar(car);
		spot.setVacancy(false);
		carsRepo.save(car);

		model.addAttribute("msg", "Car with plate number" + plateNumber + " parked at " + spot.getParkingSpot());
		return "index";
	}

}
