package com.example.module3_final.model;

import java.time.LocalDateTime;

public class TicketCard {
    private int ticketId;
    private int bookId;
    private int studentId;
    private String status;
    private String lentDate;
    private String returnDate;

    public TicketCard(int bookId, int studentId, String status, String lentDate, String returnDate) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.status = status;
        this.lentDate = lentDate;
        this.returnDate = returnDate;
    }
    public TicketCard(int ticketId, int bookId, int studentId, String status, String lentDate, String returnDate) {
        this.ticketId = ticketId;
        this.bookId = bookId;
        this.studentId = studentId;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
}
