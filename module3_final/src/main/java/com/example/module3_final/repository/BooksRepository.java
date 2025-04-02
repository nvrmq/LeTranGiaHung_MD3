package com.example.module3_final.repository;

import com.example.module3_final.model.Book;
import com.example.module3_final.model.Student;
import com.example.module3_final.model.Ticket;
import com.example.module3_final.model.TicketCard;
import com.example.module3_final.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BooksRepository implements IBooksRepository {
    @Override
    public List<Book> findAll() {
        String sql = "select * from books";
        List<Book> books = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("book_name");
                String author = resultSet.getString("book_author");
                String description = resultSet.getString("book_description");
                int quantity = resultSet.getInt("book_quantity");
                Book book = new Book(id, name, author, description, quantity);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public Book findById(int id) {
        String sql = "select * from books where id = ?";
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()){
                int bookId = resultSet.getInt("id");
                String name = resultSet.getString("book_name");
                String author = resultSet.getString("book_author");
                String description = resultSet.getString("book_description");
                int quantity = resultSet.getInt("book_quantity");
                Book book = new Book(bookId, name, author, description, quantity);
                return book;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Student> finAllStudents() {
        String sql = "select * from students";
        List<Student> students = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("student_name");
                String className = resultSet.getString("student_class");
                Student student = new Student(id, name, className);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public List<Ticket> finAllTickets() {
        String sql = "SELECT t.id AS ticket_id, s.student_name, b.book_name, t.lent_date, t.returned_date, t.status FROM tickets t JOIN students s ON t.student_id = s.id JOIN books b ON t.book_id = b.id;";
        List<Ticket> tickets = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int id  = resultSet.getInt("id");
                String bookName = resultSet.getString("book_name");
                String studentName = resultSet.getString("student_name");
                String status = resultSet.getString("status");
                String lentDate = resultSet.getString("lent_date");
                String returnedDate = resultSet.getString("returned_date");
                Ticket ticket = new Ticket(id, bookName, studentName, status, lentDate, returnedDate);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public void addTicket(TicketCard ticket) {
        String sql = "INSERT INTO tickets (book_id, student_id, status, lent_date, returned_date) VALUES(?, ?, ?, ?, ?);";
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ticket.getBookId());
            statement.setInt(2, ticket.getStudentId());
            statement.setString(3, ticket.getStatus());

            // Format lentDate
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedLentDate = inputFormat.parse(ticket.getLentDate());
            String formattedLentDate = outputFormat.format(parsedLentDate);
            statement.setString(4, formattedLentDate);

            // Format returnDate
            if (ticket.getReturnDate() != null && !ticket.getReturnDate().isEmpty()){
                Date parsedReturnDate = inputFormat.parse(ticket.getReturnDate());
                String formattedReturnDate = outputFormat.format(parsedReturnDate);
                statement.setString(5, formattedReturnDate);
            } else {
                statement.setString(5, null);
            }

            statement.execute();
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
