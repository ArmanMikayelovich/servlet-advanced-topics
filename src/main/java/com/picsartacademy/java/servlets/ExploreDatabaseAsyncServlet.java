package com.picsartacademy.java.servlets;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ExploreDatabaseAsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                // Retrieve the data source from the servlet context
                DataSource dataSource = (DataSource) asyncContext.getRequest().getServletContext().getAttribute("DATA_SOURCE");

                PrintWriter writer = asyncContext.getResponse().getWriter();
                try (Connection conn = dataSource.getConnection();
                     PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers");
                     ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        long id = resultSet.getLong("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String address = resultSet.getString("address");
                        Timestamp createdAt = resultSet.getTimestamp("created_at");
                        writer.println("ID: " + id + ", Name: " + name + ", Email: "
                                + email + ", Address: " + address + ", Created At: " + createdAt);

                    }
                    writer.write("Query completed asynchronously.");
                } catch (Exception e) {
                    writer.write("Error during database operation: " + e.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                asyncContext.complete();
            }
        });
    }
}
