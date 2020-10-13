package com.starwars.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.starwars.config.Config;

public class Database {
	
	private static BasicDataSource ds;

	static {
		createPool();
	}

	public static Connection getConnection() throws SQLException {
		if (ds == null || ds.isClosed())
			createPool();
		return ds.getConnection();
	}

	private Database() {
	}

	private static void createPool() {
		ds = new BasicDataSource();
		ds.setDriverClassName(Config.DATABASE_DRIVER_CLASS);
		ds.setUrl(Config.DATABASE_URL);
		ds.setUsername(Config.DATABASE_USERNAME);
		ds.setPassword(Config.DATABASE_PASSWORD);
		ds.setMinIdle(5);
		ds.setMaxIdle(10);
		ds.setMaxActive(-1);
		//ds.setMaxWait(5000);
	}

}
