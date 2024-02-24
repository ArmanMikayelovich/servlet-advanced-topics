package com.picsartacademy.java.persistance;

import com.zaxxer.hikari.HikariConfig;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class PostgresDataSourceFactory {

    public static HikariConfig getPostgresDataSourceConfig() {
        HikariConfig config = new HikariConfig();

        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("portNumber", "7733");
        config.addDataSourceProperty("databaseName", "postgres"); // Replace with your actual database name
        config.addDataSourceProperty("user", "postgres");
        config.addDataSourceProperty("password", "postgres");
        return config;
    }
}
