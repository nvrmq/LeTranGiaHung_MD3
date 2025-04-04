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
    public void addTicket(TicketCard borrowCard) {
        String sql = "INSERT INTO tickets (book_id, student_id, status, lent_date) VALUES(?, ?, ?, CURDATE());";
        String sql2 = "UPDATE books SET book_quantity = book_quantity - 1 where id = ?";
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(sql2);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borrowCard.getBookId());
            preparedStatement.setInt(2, borrowCard.getStudentId());
            preparedStatement.setString(3, borrowCard.getStatus());
            int effectRows = preparedStatement.executeUpdate();
            if (effectRows == 1){
                statement.setInt(1, borrowCard.getBookId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> findAllTickets() {
        String sql = "SELECT\n" +
                "    tickets.id,\n" +
                "    books.book_name,\n" +
                "    books.book_author,\n" +
                "    students.student_name,\n" +
                "    students.student_class,\n" +
                "    tickets.lent_date,\n" +
                "    tickets.returned_date,\n" +
                "    tickets.status\n" +
                "FROM\n" +
                "    tickets\n" +
                "JOIN\n" +
                "    books ON tickets.book_id = books.id\n" +
                "JOIN\n" +
                "    students ON tickets.student_id = students.id\n" +
                "WHERE\n" +
                "    tickets.status = 'lent'";
        List<Ticket> tickets = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("book_name");
                String author = resultSet.getString("book_author");
                String studentName = resultSet.getString("student_name");
                String studentClass= resultSet.getString("student_class");
                String status = resultSet.getString("status");
                String lentDate = resultSet.getString("lent_date");
                String returnedDate = resultSet.getString("returned_date");
                Ticket ticket = new Ticket(id, name, author, studentName, studentClass, status, lentDate, returnedDate);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> searchTicket(String searchName, String bookName) {
        String sql = "call searchTicket(?,?)";
        String sql2 = "call searchTicketByName(?)";
        String sql3 = "call searchTicketByBook(?)";
        List<Ticket> tickets = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = null;
            if (bookName.equals("")){
                statement = connection.prepareStatement(sql2);
                statement.setString(1, "%" + searchName + "%");
            } else if (searchName.equals("")) {
                statement = connection.prepareStatement(sql3);
                statement.setString(1, "%" + bookName + "%");
            } else if (bookName.equals("")&&searchName.equals("")) {
                tickets = findAllTickets();
                return tickets;
            } else{
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + bookName + "%");
                statement.setString(2, "%" + searchName + "%");
            }
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt("ticket_id");
                String name = resultSet.getString("book_name");
                String author = resultSet.getString("book_author");
                String studentName = resultSet.getString("student_name");
                String studentClass= resultSet.getString("student_class");
                String status = resultSet.getString("status");
                String lentDate = resultSet.getString("lent_date");
                String returnedDate = resultSet.getString("returned_date");
                Ticket ticket = new Ticket(id, name, author, studentName, studentClass, status, lentDate, returnedDate);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }
    public void returnBook(String ticketId, String bookId) {
        String sql = "UPDATE tickets SET status ='returned', returned_date = CURDATE() WHERE id = ?";
        String sql2 = "UPDATE books SET book_quantity = book_quantity + 1 where book_name = ?";
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement.setString(1, ticketId);
            preparedStatement2.setString(1, bookId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
