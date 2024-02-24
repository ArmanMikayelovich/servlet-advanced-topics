package com.picsartacademy.java.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Iterator;

public class TimeServletWithInitParams extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String string = LocalDateTime.now().toString();
        String prefix = this.getInitParameter("prefix");
        writer.write(prefix + string);

        writer.write("</br>");

        Iterator<String> iterator = this.getInitParameterNames().asIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            writer.write(next);
            writer.write("</br>");
        }
    }
}
