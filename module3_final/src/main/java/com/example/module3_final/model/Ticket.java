package com.example.module3_final.model;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private String bookName;
    private String author;
    private String studentName;
    private String studentClass;
    private String status;
    private String lentDate;
    private String returnDate;

    public Ticket(int ticketId, String bookName, String author, String studentName, String studentClass, String status, String lentDate, String returnDate) {
        this.ticketId = ticketId;
        this.bookName = bookName;
        this.author = author;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.status = status;
        this.lentDate = lentDate;
        this.returnDate = returnDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLentDate() {
        return lentDate;
    }

    public void setLentDate(String lentDate) {
        this.lentDate = lentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}
