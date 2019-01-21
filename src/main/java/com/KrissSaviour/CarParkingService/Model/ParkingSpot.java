package com.KrissSaviour.CarParkingService.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * This is the parking spot model class use to create the parking spot table in the DB
 * */
@Entity
public class ParkingSpot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false,unique=true)
	private String spotName;
	private Boolean vacancy;
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
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
	public ParkingSpot(int id, String spotName, boolean vacancy) {
		this.id = id;
		this.spotName = spotName;
		this.vacancy = vacancy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setParkingSpot(String parkingSpot) {
		this.spotName = parkingSpot;
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

	public Boolean getVacancy() {
		return vacancy;
	}

	public void setVacancy(Boolean vacancy) {
		this.vacancy = vacancy;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	@Override
	public String toString() {
		return "ParkingSpot [id=" + id + ", spotName=" + spotName + ", vacancy=" + vacancy + ", car=" + car + "]";
	}

}
