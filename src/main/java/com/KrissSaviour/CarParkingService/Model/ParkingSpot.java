package com.KrissSaviour.CarParkingService.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * This is the parking spot model class use to create the parking spot table in the DB
 * */
@Entity
public class ParkingSpot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	@NotNull
	private String parkingSpot;
	
	private Boolean vacancy;

	@OneToOne(fetch=FetchType.LAZY)
	private Cars car;

	/**
	 * No ARGS
	 */
	public ParkingSpot() {

	}

	/**
	 * @param id
	 * @param parkingSpot
	 * @param vacancy
	 */
	public ParkingSpot(int id, @NotEmpty @NotNull String parkingSpot, @NotEmpty @NotNull boolean vacancy) {
		super();
		this.id = id;
		this.parkingSpot = parkingSpot;
		this.vacancy = vacancy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(String parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	public boolean isVacancy() {
		return vacancy;
	}

	public void setVacancy(boolean vacancy) {
		this.vacancy = vacancy;
	}

	public Cars getCar() {
		return car;
	}

	public void setCar(Cars car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "ParkingSpot [id=" + id + ", parkingSpot=" + parkingSpot + ", vacancy=" + vacancy + ", car=" + car
				+ ", getId()=" + getId() + ", getParkingSpot()=" + getParkingSpot() + ", isVacancy()=" + isVacancy()
				+ ", getCar()=" + getCar() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
