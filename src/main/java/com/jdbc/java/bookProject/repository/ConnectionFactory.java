package com.jdbc.java.bookProject.repository;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionFactory {

	private BasicDataSource dataSource = new BasicDataSource();
	
	public ConnectionFactory() {
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/BookDB?useSSL=false&allowPublicKeyRetrieval=true");
		dataSource.setUsername("sampleUser");
		dataSource.setPassword("password");
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(2);
		dataSource.setValidationQuery("SELECT 1");
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
