package com.mohit.gojek.controller;

import com.mohit.gojek.model.ParkingSlot;
import com.mohit.gojek.service.ParkingSlotService;
import com.mohit.gojek.service.ParkingSlotServiceImpl;

public class ParkingSlotController {

	private ParkingSlotService parkingSlotService = new ParkingSlotServiceImpl();

	public String createParkingLot(int totalSlot) {
		int count = 0;
		for (int index = 1; index <= totalSlot; index++) {
			ParkingSlot parkingSlot = new ParkingSlot(Long.valueOf(index), true);
			if (parkingSlotService.saveParkingSlot(parkingSlot) != null) {
				count++;
			}
		}
		return "Created a parking lot with " + count + " slots";
	}

}
