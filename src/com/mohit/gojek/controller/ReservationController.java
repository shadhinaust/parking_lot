package com.mohit.gojek.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mohit.gojek.model.Car;
import com.mohit.gojek.model.ParkingSlot;
import com.mohit.gojek.model.Reservation;
import com.mohit.gojek.service.CarService;
import com.mohit.gojek.service.CarServiceImpl;
import com.mohit.gojek.service.ParkingSlotService;
import com.mohit.gojek.service.ParkingSlotServiceImpl;
import com.mohit.gojek.service.ReservationService;
import com.mohit.gojek.service.ReservationServiceImpl;

public class ReservationController {

	private ParkingSlotService parkingSlotService = new ParkingSlotServiceImpl();
	private CarService carService = new CarServiceImpl();
	private ReservationService reservationService = new ReservationServiceImpl();

	public String parkACar(String registrationNumber, String color) {
		Car car = carService.getCarByRegistrationNumber(registrationNumber);
		if(car != null) {
			Long parkingSlotId = reservationService.getParkingSlotIdByCarIdAndStatu(car.getId(), true);
			if(parkingSlotId != null) {
				return "Already allocated at slot number: " + parkingSlotService.getById(parkingSlotId).getSlotNumber();
			}
		}
		
		List<Long> availableParkingSlotNumbers = parkingSlotService.getAvailableParkingSlotNumbers();
		if (availableParkingSlotNumbers == null || availableParkingSlotNumbers.isEmpty()) {
			return "Sorry, parking lot is full";
		}
		Long nearestParkingSlotNumber = availableParkingSlotNumbers.stream().min(Comparator.naturalOrder()).get();
		ParkingSlot parkingSlot = parkingSlotService.getBySlotNumber(nearestParkingSlotNumber);
		if (parkingSlot == null) {
			return "Unexpected error. Please try again.";
		}
		parkingSlot.setStatus(false);
		parkingSlot = parkingSlotService.updateParkingSlot(parkingSlot);

		if (car == null) {
			car = carService.saveCar(new Car(registrationNumber, color));
		}

		Reservation reservation = new Reservation(parkingSlot.getId(), car.getId(), true);
		reservation = reservationService.save(reservation);
		return "Allocated slot number: " + nearestParkingSlotNumber;
	}

	public String leaveASlot(Long parkingSlotNumber) {
		ParkingSlot parkingSlot = parkingSlotService.getBySlotNumber(parkingSlotNumber);
		if (parkingSlot == null) {
			return "Unexpected error. Please try again.";
		}
		Reservation reservation = reservationService.getByParkingSlotIdAndStatu(parkingSlot.getId(), true);
		if (reservation == null) {
			return "Unexpected error. Please try again.";
		}
		parkingSlot.setStatus(true);
		parkingSlot = parkingSlotService.updateParkingSlot(parkingSlot);
		reservation.setStatus(false);
		reservationService.update(reservation);

		return "Slot number " + parkingSlotNumber + " is free";
	}
	
	public String status() {
		List<String> status = new ArrayList<>();
		status.add("Slot No.\tRegistration No\t\tColour");
		List<Reservation> reservations = reservationService.getByStatus(true);
		reservations.forEach(reservation -> {
			Long slonNumber = parkingSlotService.getById(reservation.getParkingSlotId()).getSlotNumber();
			Car car = carService.getById(reservation.getCarId());
			status.add(slonNumber + "\t\t" + car.getRegistrationNumber() + "\t\t" + car.getColor());
		});
		return String.join(System.lineSeparator(), status);
	}
	
	public String getColorSpecificRegistrationNumburs(String color) {
		List<String> response = reservationService.getByStatus(true).stream()
				.map(reservation -> carService.getById(reservation.getCarId()))
				.filter(car -> car.getColor().equalsIgnoreCase(color)).map(car -> car.getRegistrationNumber())
				.collect(Collectors.toList());
		return String.join(", ", response);
	}
	
	public String getColorSpecificParkingSlotNumburs(String color) {
		List<String> response = new ArrayList<>();
		reservationService.getByStatus(true).forEach(reservation -> {
			Car car = carService.getById(reservation.getCarId());
			if (car.getColor().equalsIgnoreCase(color)) {
				response.add(
						String.valueOf(parkingSlotService.getById(reservation.getParkingSlotId()).getSlotNumber()));
			}
		});
		return String.join(", ", response);
	}
	
	public String getRegistrationNumberSpecificParkingSlotNumber(String registrationNumber) {
		Car car = carService.getCarByRegistrationNumber(registrationNumber);
		if (car == null) {
			return "Not found";
		}
		Long parkingSlotId = reservationService.getParkingSlotIdByCarIdAndStatu(car.getId(), true);
		if (parkingSlotId == null) {
			return "Not found";
		}
		ParkingSlot parkingSlot = parkingSlotService.getById(parkingSlotId);
		if (parkingSlot == null) {
			return "Not found";
		}
		return String.valueOf(parkingSlot.getSlotNumber());
	}
}
