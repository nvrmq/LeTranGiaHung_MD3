package com.example.ss10_jstl.controller;

import com.example.ss10_jstl.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customers")
public class CustomerServlet extends HttpServlet {
    private static List<Customer> customers = new ArrayList<>();
    static {
        customers.add(new Customer(1, "Mai Văn Hoàn", "1983-08-20", "Hà Nội"));
        customers.add(new Customer(2, "Nguyễn Văn Nam", "1983-08-21", "Bắc Giang"));
        customers.add(new Customer(3, "Nguyễn Thái Hoà", "1983-08-22", "Nam Định"));
        customers.add(new Customer(4, "Trần Đăng Khoa", "1983-08-17", "Hà Tây"));
        customers.add(new Customer(5, "Nguyễn Đình Thi", "1983-08-19", "Hà Nội"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/views/customer/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
