package com.mohit.gojek.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider implements DatabaseProvider {

	static Connection connection = null;

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
}
