package com.picsartacademy.java.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LastVisitServlet extends HttpServlet {

    private static final String LAST_VISIT_COOKIE_NAME = "lastVisit";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Find the lastVisit cookie
        Cookie lastVisitCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LAST_VISIT_COOKIE_NAME.equals(cookie.getName())) {
                    // URL decode the value when reading it
                    String value = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    lastVisitCookie = new Cookie(LAST_VISIT_COOKIE_NAME, value);
                    break;
                }
            }
        }

        String lastVisitTime = "This is your first visit!";
        // If the cookie was found, read the last visit time
        if (lastVisitCookie != null) {
            lastVisitTime = "Your last visit was on " + lastVisitCookie.getValue();
        }

        // Update the lastVisit cookie with the current time
        String now = dtf.format(LocalDateTime.now());
        // URL encode the value when setting it
        String encodedNow = URLEncoder.encode(now, StandardCharsets.UTF_8);
        Cookie newLastVisitCookie = new Cookie(LAST_VISIT_COOKIE_NAME, encodedNow);
        newLastVisitCookie.setMaxAge(60 * 60 * 24 * 365); // Cookie will be stored for one year
        response.addCookie(newLastVisitCookie);

        // Write the response
        response.setContentType("text/html");
        response.getWriter().write("<html><body>");
        response.getWriter().write("<h1>Welcome to our website!</h1>");
        response.getWriter().write("<p>" + lastVisitTime + "</p>");
        response.getWriter().write("</body></html>");
    }
}
