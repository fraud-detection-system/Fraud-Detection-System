package com.stream.accounting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresDB {
	Connection c = null;

	public static void main(String args[]) {
		PostgresDB postgresDB = new PostgresDB();
		postgresDB.getConnection();
		Entity entity = new Entity();
		entity.setCompanyId("123");
		entity.setEntityId("456");
		entity.setData("test");
		postgresDB.insert(entity);
		postgresDB.closeConnection();
	}

	public void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c = null;
	}

	public Connection getConnection() {
		if (c != null) {
			return c;
		}
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"mysecretpassword");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return c;
	}

	public void insert(Entity entity) {

		try {
			Connection c = getConnection();

			c.setAutoCommit(false);

			Statement stmt = c.createStatement();
			String sql = "INSERT INTO \"Entity\" (\"companyId\",\"entityId\",\"data\") " + "VALUES ('" + entity.getCompanyId() + "', '"
					+ entity.getEntityId() + "', '" + entity.getData() + "');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

}
