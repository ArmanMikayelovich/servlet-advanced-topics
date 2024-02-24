package com.picsartacademy.java.listeners;

import com.picsartacademy.java.persistance.PostgresDataSourceFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JDBCDataSourceListener implements ServletContextListener {

    private HikariDataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize the database connection pool
        HikariConfig config = PostgresDataSourceFactory.getPostgresDataSourceConfig();

        // Additional HikariCP settings - these are optional and could be tweaked as necessary
        config.setMaximumPoolSize(10); // The maximum number of connections in the pool
        config.setMinimumIdle(5);      // The minimum number of idle connections HikariCP tries to maintain
        config.setIdleTimeout(300000); // The maximum amount of time that a connection is allowed to sit idle in the pool

        dataSource = new HikariDataSource(config);

        // Store the data source in the application scope
        sce.getServletContext().setAttribute("DATA_SOURCE", dataSource);
        System.out.println("Database connection pool has been initialized.");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            dataSource.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Database connection pool has been shut down.");
    }
}
