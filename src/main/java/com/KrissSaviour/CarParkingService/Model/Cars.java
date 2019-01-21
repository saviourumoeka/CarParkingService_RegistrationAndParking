package com.KrissSaviour.CarParkingService.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



/*
 * This is the car model class use to create the car table in the DB
 * */

@Entity
public class Cars {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false,unique=true)
	private String plateNumber;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy="car")
	private ParkingSpot parkingSpot;

	/**
	 * No Args Constructor
	 */
	public Cars() {

	}

	/**
	 * @param id
	 * @param carPlateNumber
	 */
	public Cars(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	@Override
	public String toString() {
		return "Cars [id=" + id + ", plateNumber=" + plateNumber + ", parkingSpot=" + parkingSpot + "]";
	}

	

	
}
