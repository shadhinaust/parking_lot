package com.mohit.gojek.service;

import com.mohit.gojek.model.Car;

public interface CarService {
	
	public Car saveCar(Car car);
	
	public Car getCarByRegistrationNumber(String registrationNumber);
	
	public Car getById(Long id);
}
