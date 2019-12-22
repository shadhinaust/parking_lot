package com.mohit.gojek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mohit.gojek.connection.ConnectionProvider;
import com.mohit.gojek.model.Car;
import com.mohit.gojek.model.ParkingSlot;

public class CarDaoImpl implements CarDao {

	private static Connection connection;
	private static PreparedStatement ps;

	@Override
	public Car save(Car car) throws SQLException {
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("insert into car(registration_number, color) values(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, car.getRegistrationNumber());
			ps.setString(2, car.getColor());
			ps.executeUpdate();
			ResultSet resultSet = ps.getGeneratedKeys();
			while (resultSet.next()) {
				car.setId(resultSet.getLong(1));
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
			car = null;
		} finally {
			ps.close();
			connection.close();
		}
		return car;
	}

	@Override
	public Car update(Car car) throws SQLException {
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("update car set color=? where id=?");
			ps.setString(1, car.getColor());
			ps.setLong(2, car.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			car = null;
		} finally {
			ps.close();
			connection.close();
		}
		return car;
	}

	@Override
	public Car get(Long id) throws SQLException {
		Car car = null;
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("select * from car where id=?");
			ps.setLong(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				car = new Car();
				car.setId(resultSet.getLong("id"));
				car.setRegistrationNumber(resultSet.getString("registration_number"));
				car.setColor(resultSet.getString("color"));
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			connection.close();
		}
		return car;
	}

	@Override
	public Car getByRegistrationNumber(String registrationNumber) throws SQLException {
		Car car = null;
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("select * from car where registration_number like ?");
			ps.setString(1, registrationNumber);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				car = new Car();
				car.setId(resultSet.getLong("id"));
				car.setRegistrationNumber(resultSet.getString("registration_number"));
				car.setColor(resultSet.getString("color"));
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			connection.close();
		}
		return car;
	}
}
