package com.mohit.gojek.service;

import java.util.List;

import com.mohit.gojek.model.ParkingSlot;

public interface ParkingSlotService {
	
	public ParkingSlot saveParkingSlot(ParkingSlot parkingSlot);
	
	public ParkingSlot updateParkingSlot(ParkingSlot parkingSlot);
	
	public List<Long> getAvailableParkingSlotNumbers();
	
	public ParkingSlot getBySlotNumber(Long slotNumber);
	
	public ParkingSlot getById(Long id);
}
