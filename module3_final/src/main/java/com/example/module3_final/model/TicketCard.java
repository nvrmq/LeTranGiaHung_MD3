package com.example.module3_final.model;

import java.time.LocalDateTime;
import java.util.Date;

public class TicketCard {
    private int ticketId;
    private int bookId;
    private int studentId;
    private String status;
    private Date lentDate;
    private Date returnDate;

    public TicketCard(int bookId, int studentId, String status, Date lentDate, Date returnDate) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.status = status;
        this.lentDate = lentDate;
        this.returnDate = returnDate;
    }
    public TicketCard(int ticketId, int bookId, int studentId, String status, Date lentDate, Date returnDate) {
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

    public Date getLentDate() {
        return lentDate;
    }

    public void setLentDate(Date lentDate) {
        this.lentDate = lentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
