package com.mohit.gojek.service;

import java.sql.SQLException;

import com.mohit.gojek.dao.CarDao;
import com.mohit.gojek.dao.CarDaoImpl;
import com.mohit.gojek.model.Car;

public class CarServiceImpl implements CarService {
	
	private CarDao carDao = new CarDaoImpl();

	@Override
	public Car saveCar(Car car) {
		try {
			return carDao.save(car);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car getCarByRegistrationNumber(String registrationNumber) {
		try {
			return carDao.getByRegistrationNumber(registrationNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car getById(Long id) {
		try {
			return carDao.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
