package com.mohit.gojek.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionProvider implements DatabaseProvider {

	private static Connection connection = null;
	private static PreparedStatement ps;

	private static final String CREATE_TABLE_CAR = "CREATE TABLE IF NOT EXISTS car ("
			+ "  id int(11) NOT NULL AUTO_INCREMENT," + "  registration_number varchar(16) NOT NULL,"
			+ "  color varchar(16) NOT NULL," + "  PRIMARY KEY (id),"
			+ "  UNIQUE KEY registration_number (registration_number)" + ");";

	private static final String CREATE_TABLE_PARKING_SLOT = "CREATE TABLE IF NOT EXISTS parking_slot ("
			+ "  id int(11) NOT NULL AUTO_INCREMENT," + "  slot_number int(11) NOT NULL,"
			+ "  status tinyint(1) NOT NULL," + "  PRIMARY KEY (id)," + "  UNIQUE KEY slot_number (slot_number)" + ");";

	private static final String CREATE_TABLE_RESERVATION = "CREATE TABLE IF NOT EXISTS reservation ("
			+ " id int(11) NOT NULL AUTO_INCREMENT," + "  parking_slot_id int(11) NOT NULL,"
			+ " car_id int(11) NOT NULL," + "  status tinyint(1) NOT NULL," + " PRIMARY KEY (id)" + ");";

	private static final String TRUNCATE_CAR = "TRUNCATE car;";
	private static final String TRUNCATE_PARKING_SLOT = "TRUNCATE parking_slot;";
	private static final String TRUNCATE_RESERVATION = "TRUNCATE reservation;";

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(connectionUrl, username, password);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}

	public static boolean createTables() {
		boolean status = false;
		connection = ConnectionProvider.getConnection();
		try {
			ps = connection.prepareStatement(CREATE_TABLE_CAR);
			ps.execute();
			ps = connection.prepareStatement(CREATE_TABLE_PARKING_SLOT);
			ps.execute();
			ps = connection.prepareStatement(CREATE_TABLE_RESERVATION);
			ps.execute();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public static boolean cleanupData() {
		boolean status = false;
		connection = ConnectionProvider.getConnection();
		try {
			ps = connection.prepareStatement(TRUNCATE_RESERVATION);
			ps.execute();
			ps = connection.prepareStatement(TRUNCATE_CAR);
			ps.execute();
			ps = connection.prepareStatement(TRUNCATE_PARKING_SLOT);
			ps.execute();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
}
