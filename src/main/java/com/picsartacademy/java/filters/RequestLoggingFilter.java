package com.picsartacademy.java.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

public class RequestLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code here (if needed)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


            // Retrieve the data source from the servlet context
            DataSource dataSource = (DataSource) request.getServletContext().getAttribute("DATA_SOURCE");

        // Gather request information
        String ip = request.getRemoteAddr();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String method = httpRequest.getMethod();
        String path = httpRequest.getRequestURI();
        String params = httpRequest.getQueryString() != null ? httpRequest.getQueryString() : "";

        // Insert request information into the database
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO request_audit (ip, method, path, params) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, ip);
            stmt.setString(2, method);
            stmt.setString(3, path);
            stmt.setString(4, params);
            stmt.executeUpdate();
        } catch ( SQLException e) {
            throw new ServletException("Database error while logging request", e);
        }

        // Continue the filter chain
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        // Cleanup code here (if needed)
    }
}
