package com.mohit.gojek.model;

public class ParkingSlot {
	
	private Long id;
	private Long slotNumber;
	private Boolean status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(Long slotNumber) {
		this.slotNumber = slotNumber;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
