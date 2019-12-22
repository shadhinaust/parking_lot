package com.mohit.gojek.dao;

import java.sql.SQLException;
import java.util.List;

import com.mohit.gojek.model.Car;

public interface CarDao {

	public Car save(Car car) throws SQLException;

	public Car update(Car car) throws SQLException;

	public Car get(Long id) throws SQLException;
	
	public Car getByRegistrationNumber(String registrationNumber) throws SQLException;
}
