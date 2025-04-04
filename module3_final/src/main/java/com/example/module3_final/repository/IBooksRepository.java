package com.example.module3_final.repository;

import com.example.module3_final.model.Book;
import com.example.module3_final.model.Student;
import com.example.module3_final.model.Ticket;
import com.example.module3_final.model.TicketCard;

import java.util.List;

public interface IBooksRepository {
    List<Book> findAll();
    Book findById(int id);
    List<Student> finAllStudents();
    void addTicket(TicketCard ticket);
    List<Ticket> findAllTickets();
    List<Ticket> searchTicket(String searchName, String bookName);
    void returnBook(String ticketId, String bookId);
}
