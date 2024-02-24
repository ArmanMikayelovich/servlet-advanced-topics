package com.picsartacademy.java.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartWithSessionServlet extends HttpServlet {
    private static final String SHOPPING_CART_ATTRIBUTE_NAME = "cart";
    private static final String ITEM_PARAM_NAME = "item";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> cart = (List<String>) req.getSession().getAttribute(SHOPPING_CART_ATTRIBUTE_NAME);
        if (cart == null) {
            cart = new ArrayList<>();
            req.getSession().setAttribute(SHOPPING_CART_ATTRIBUTE_NAME,cart);
        }
        cart.forEach(item -> {

            try {
                var writer = resp.getWriter();
                writer.write(item + " \n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
        // Get the current session object, create one if necessary
        HttpSession session = request.getSession();

        // Retrieve the shopping cart from the session object
        List<String> cart = (List<String>) session.getAttribute(SHOPPING_CART_ATTRIBUTE_NAME);
        if (cart == null) {
            // This is the user's first item, so create a new list
            cart = new ArrayList<>();
            session.setAttribute(SHOPPING_CART_ATTRIBUTE_NAME, cart);
        }

        // Add the item to the cart
        String newItem = request.getParameter(ITEM_PARAM_NAME);
        cart.add(newItem);

        // Redirect to shopping page or cart view
        response.getWriter().write("added item: " + newItem);
    }

}
