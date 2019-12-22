package com.mohit.gojek.service;

import java.util.List;

import com.mohit.gojek.model.Reservation;

public interface ReservationService {

	public Reservation save(Reservation reservation);

	public Reservation update(Reservation reservation);

	public Reservation getByParkingSlotIdAndStatu(Long parkingSlotId, Boolean status);
	
	public Long getParkingSlotIdByCarIdAndStatu(Long carId, Boolean status);
	
	public List<Reservation> getByStatus(Boolean status);
}
