package com.mohit.gojek.dao;

import java.sql.Connection;

import com.mohit.gojek.connection.ConnectionProvider;

public class ParkingSlotDaoImpl implements ParkingSlotDao {

	private static ConnectionProvider connectionProvider = null;

	@Override
	public Connection getConnection() {
		return connectionProvider.getConnection();
	}
}
