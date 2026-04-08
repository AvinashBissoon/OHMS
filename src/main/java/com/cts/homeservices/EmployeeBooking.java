package com.cts.homeservices;

public class EmployeeBooking {
    // These represent the data for ONE row in your table
    private int bookingId;
    private String date;
    private String time;
    private String service;
    private String details;
    private String address;
    private String city;
    private String assignedTo;
    private String status;

    // The Constructor: used when we pull data from the Database
    public EmployeeBooking(int bookingId, String date, String time, String service, String details, String address, String city, String assignedTo, String status) {
        this.bookingId = bookingId;
        this.date = date;
        this.time = time;
        this.service = service;
        this.details = details;
        this.address = address;
        this.city = city;
        this.assignedTo = assignedTo;
        this.status = status;
    }

    //Getters. JavaFX TableView CANNOT see the data without these.
    public int getBookingId(){ return bookingId; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getService() { return service; }
    public String getDetails() { return details; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getAssignedTo() { return assignedTo; }
    public String getStatus() { return status; }

    // Setters
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setService(String service) { this.service = service; }
    public void setDetails(String details) { this.details = details; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    public void setStatus(String status) { this.status = status; }
}