package com.mohit.gojek.connection;

public interface DatabaseProvider {

	String driver = "com.mysql.cj.jdbc.Driver";
	String username = "jek";
	String password = "123456";
	String connectionUrl = "jdbc:mysql://localhost:5723/parking_lot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

}
