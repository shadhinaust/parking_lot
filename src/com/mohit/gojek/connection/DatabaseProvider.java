package com.mohit.gojek.connection;

public interface DatabaseProvider {

	String driver = "com.mysql.cj.jdbc.Driver";
	String username = "bms";
	String password = "";
	String connectionUrl = "jdbc:mysql://localhost:3306/parking_lot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

}
