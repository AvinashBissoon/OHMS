package com.cts.homeservices;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String country;
    private String mobilePhone;
    private String email;

    public Customer(int customerId, String firstName, String lastName, String email, String mobilePhone, String streetAddress1,
                    String streetAddress2, String city, String country) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.country = country;

    }

    // Getters for TableView
    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getMobilePhone() { return mobilePhone; }
    public String getStreetAddress1() { return streetAddress1; }
    public String getStreetAddress2() { return streetAddress2; }
    public String getCity() { return city; }
    public String getCountry() { return country; }
}