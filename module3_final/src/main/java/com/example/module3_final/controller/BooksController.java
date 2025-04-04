package com.example.module3_final.controller;

import com.example.module3_final.model.Book;
import com.example.module3_final.model.Student;
import com.example.module3_final.model.Ticket;
import com.example.module3_final.model.TicketCard;
import com.example.module3_final.service.BooksService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
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
                break;
            case "search":
                searchName(req,resp);
                break;
            case "return":
                returnBooks(req,resp);
            default:
                showBooks(req, resp);
        }
    }

    private void returnBooks(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        try {
            booksService.returnBook(id);
            List<Ticket> tickets = booksService.findAllTicket();
            req.setAttribute("tickets", tickets);
            req.getRequestDispatcher("current.jsp").forward(req,resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter("searchName");
        String bookName = req.getParameter("bookName");
        List<Ticket> tickets = booksService.searchTicket(searchName, bookName);
        req.setAttribute("tickets", tickets);
        req.getRequestDispatcher("current.jsp").forward(req,resp);
    }

    private void showCurrent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ticket> tickets = booksService.findAllTicket();
        req.setAttribute("tickets", tickets);
        req.getRequestDispatcher("current.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                try {
                    addTicket(req,resp);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void addTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String returnDateStr = req.getParameter("returnDate");
        String status ="lent";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date borrowDate = new Date();
        Date returnDate = sdf.parse(returnDateStr);
        if (returnDate.before(borrowDate)) {
            req.setAttribute("error", "Invalid date");
            showLendForm(req, resp);
            return;
        }
        TicketCard ticketCard = new TicketCard(bookId, studentId, status, borrowDate, returnDate);
        booksService.addTicket(ticketCard);
        showBooks(req,resp);
    }

    private void showLendForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = booksService.findById(id);
        List<Student> students = booksService.findAllStudent();
        String localTime =LocalDate.now().toString();
        req.setAttribute("book", book);
        req.setAttribute("students", students);
        req.setAttribute("localTime", localTime);
        req.getRequestDispatcher("lend.jsp").forward(req,resp);
    }

    private void showBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = booksService.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
