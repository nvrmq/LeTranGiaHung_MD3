package com.example.demo_jsp_servlet.codegym.ss9discount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DiscountServlet", value = "/abc")
public class DiscountPrice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        Double listPrice = Double.parseDouble(request.getParameter("listPrice"));
        Double discountPercent = Double.parseDouble(request.getParameter("discountPercent"));

        double discountAmount = listPrice * discountPercent * 0.01;
        double discountPrice = listPrice - discountAmount;

        request.setAttribute("description", description);
        request.setAttribute("listPrice", listPrice);
        request.setAttribute("discountPercent", discountPercent);
        request.setAttribute("discountAmount", discountAmount);
        request.setAttribute("discountPrice", discountPrice);
        request.getRequestDispatcher("display-discount.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("display-discount.jsp").forward(request, response);
    }
}
