package com.mohit.gojek.model;

public class Reservation {
	private Long id;
	private Long parkingSlotId;
	private Long carId;
	private Boolean status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParkingSlotId() {
		return parkingSlotId;
	}
	public void setParkingSlotId(Long parkingSlotId) {
		this.parkingSlotId = parkingSlotId;
	}
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
