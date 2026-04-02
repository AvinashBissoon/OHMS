package com.cts.homeservices;

public class Booking {
    // These represent the data for ONE row in your table
    private int bookingId;
    private String date;
    private String time;
    private String service;
    private String details;
    private String assignedTo;
    private String status;

    // The Constructor: used when we pull data from the Database
    public Booking(int bookingId, String date, String time, String service, String details, String assignedTo, String status) {
        this.bookingId = bookingId;
        this.date = date;
        this.time = time;
        this.service = service;
        this.details = details;
        this.assignedTo = assignedTo;
        this.status = status;
    }

    // IMPORTANT: Getters. JavaFX TableView CANNOT see the data without these.
    public int getBookingId(){ return bookingId; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getService() { return service; }
    public String getDetails() { return details; }
    public String getAssignedTo() { return assignedTo; }
    public String getStatus() { return status; }

    // Setters (Optional, but good to have)
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setService(String service) { this.service = service; }
    public void setDetails(String details) { this.details = details; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    public void setStatus(String status) { this.status = status; }
}