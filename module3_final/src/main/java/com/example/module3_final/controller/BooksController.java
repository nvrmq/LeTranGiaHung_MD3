package com.example.module3_final.controller;

import com.example.module3_final.model.Book;
import com.example.module3_final.model.Student;
import com.example.module3_final.model.TicketCard;
import com.example.module3_final.service.BooksService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BooksController", value = "/book")
public class BooksController extends HttpServlet {
    private final BooksService booksService = new BooksService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "lend":
                showLendForm(req,resp);
                break;
            case "current":
                showCurrent(req,resp);
            default:
                showBooks(req, resp);
        }
    }

    private void showCurrent(HttpServletRequest req, HttpServletResponse resp) {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addTicket(req,resp);
        }
    }

    private void addTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        int studentId = Integer.parseInt(req.getParameter("studentSelect"));
        String status = "lent";
        String pattern = "dd-MM-yyyy";
        String lentDate =new SimpleDateFormat(pattern).format(new Date());
        String returnDate = req.getParameter("returnDate");
        TicketCard ticketCard = new TicketCard(bookId, studentId, status, lentDate, returnDate);
        booksService.addTicket(ticketCard);
        showBooks(req,resp);
    }

    private void showLendForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = booksService.findById(id);
        List<Student> students = booksService.findAllStudent();
        String pattern = "dd-MM-yyyy";
        String localTime =new SimpleDateFormat(pattern).format(new Date());
        req.setAttribute("book", book);
        req.setAttribute("students", students);
        req.setAttribute("localTime", localTime);
        req.getRequestDispatcher("lend.jsp").forward(req,resp);
    }

    private void showBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = booksService.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }
}
