package com.picsartacademy.java.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Iterator;

public class ServletWithContextParams extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        String contextPath = servletContext.getContextPath();
        PrintWriter writer = resp.getWriter();

        writer.write("context path: " + contextPath);

        writer.write('\n');

        Iterator<String> iterator = servletContext.getInitParameterNames().asIterator();
        List<String> paramNames = new ArrayList<>();
        while (iterator.hasNext()) {
            String paramName = iterator.next();
            paramNames.add(paramName);
        }

        paramNames.forEach(name -> {
            String value = servletContext.getInitParameter(name);
            writer.write(name + " : " + value + "\n");
        });
    }
}
