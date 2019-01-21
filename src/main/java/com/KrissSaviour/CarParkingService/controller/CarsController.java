package com.KrissSaviour.CarParkingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.KrissSaviour.CarParkingService.Model.Cars;
import com.KrissSaviour.CarParkingService.Model.ParkingSpot;
import com.KrissSaviour.CarParkingService.Repository.CarsRepository;

/*
 * Cars Controller*/

@Controller
public class CarsController {
	@Autowired
	private CarsRepository carsRepo;
	
	private ModelMap model;

	@Autowired
	// @PersistenceContext
	EntityManager em;

	// Query to find one free spot
	public ParkingSpot findOneVacancy() {
		try {
			return em.createQuery("Select p FROM ParkingSpot p WHERE p.vacancy=true", ParkingSpot.class)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	};

	// Query to update car spot
	public Cars updateCarParkingSpot(int id, ParkingSpot parkingSpot) {
		Cars car = em.find(Cars.class, id);
	
		car.setParkingSpot(parkingSpot);
		parkingSpot =em.find(ParkingSpot.class,parkingSpot.getId());
		
		 parkingSpot.setCar(car);
		 parkingSpot.setVacancy(false);

		em.merge(car);
		em.merge(parkingSpot);
		return car;
	};

	// Returns all cars
	@GetMapping
	public List<Cars> getAllCars() {
		return carsRepo.findAll();
	}

	// loads Index page
	@RequestMapping("/")
	public String index(ModelMap mode) {
		if(model == null) {
			return "index";
		}
		mode.addAttribute("msg", model.get("msg"));
		return "index";
	}

//Parking car in a spot
	@RequestMapping("/parkCar")
	@Transactional
	public String getCarByPlateNumber(@RequestParam("plateNumber") String plateNumber, ModelMap mode) {
		
		if(plateNumber.isEmpty()) {
			model=mode.addAttribute("msg", "Plate number cannot be empty");
			return "redirect:/";
		}
		plateNumber = plateNumber.toUpperCase().trim();
		// Matching car with plate number in DB
		Cars car = carsRepo.findByPlateNumber(plateNumber);

		// if car is no registered
		// registration and parking of new car
		if (car==null) {
			Cars cars = new Cars(plateNumber);
			carsRepo.save(cars);
			//Getting Saved Car From DB
			cars = carsRepo.findByPlateNumber(plateNumber);
			ParkingSpot spot = findOneVacancy();
			if(spot==null) {
				model=mode.addAttribute("msg", "Sorry We Are Out Of Parking Spots, Please Check Again Later");
				return "redirect:/";
			}
			updateCarParkingSpot(cars.getId(), spot);

			model=mode.addAttribute("msg", "Car with plate number " + plateNumber + " parked at " + spot.getSpotName());
			return "redirect:/";
		}

		if (car.getParkingSpot() !=null) {
			model=mode.addAttribute("msg",
					"Car with plate number " + plateNumber + " Already parked at " + car.getParkingSpot().getSpotName());
			return "redirect:/";
		}

		ParkingSpot spot = findOneVacancy();
		if(spot==null) {
			model=mode.addAttribute("msg", "Sorry We Are Out Of Parking Spots, Please Check Again Later");
			return "redirect:/";
		}
		updateCarParkingSpot(car.getId(), spot);

	model =	mode.addAttribute("msg", "Car with plate number " + plateNumber + " parked at " + spot.getSpotName());

		return "redirect:/";

	}

}
