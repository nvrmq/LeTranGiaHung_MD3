package com.example.module3_final.service;

import com.example.module3_final.model.Book;
import com.example.module3_final.model.Student;
import com.example.module3_final.model.TicketCard;
import com.example.module3_final.repository.BooksRepository;

import java.util.List;

public class BooksService implements IBookService{
    private final BooksRepository booksRepository = new BooksRepository();
    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        return booksRepository.findById(id);
    }

    @Override
    public List<Student> findAllStudent() {
        return booksRepository.finAllStudents();
    }

    @Override
    public void addTicket(TicketCard ticket) {
        booksRepository.addTicket(ticket);
    }
}
