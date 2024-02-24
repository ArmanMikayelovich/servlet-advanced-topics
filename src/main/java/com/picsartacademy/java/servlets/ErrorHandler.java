package com.picsartacademy.java.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.*;

public class ErrorHandler extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processError(req, resp);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Analyze the servlet exception
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();



        out.write("<h3>Error Details</h3>");
        out.write(throwable.toString());
    }
}
