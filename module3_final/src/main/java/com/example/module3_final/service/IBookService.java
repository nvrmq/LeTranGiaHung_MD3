package com.example.module3_final.service;

import com.example.module3_final.model.Book;
import com.example.module3_final.model.Student;
import com.example.module3_final.model.Ticket;
import com.example.module3_final.model.TicketCard;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book findById(int id);
    List<Student> findAllStudent();
    void addTicket(TicketCard ticket);
    List<Ticket> findAllTicket();
}
