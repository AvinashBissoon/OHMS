package com.cts.homeservices;

public class UserSession {
    private static int userId;
    private static int customerId;
    private static int employeeId;
    private static String firstName;
    private static String lastName;
    private static String email;

    // Call this in your LoginViewController after a successful login
    public static void init(int id, String fName, String lName, String userEmail) {
        userId = id;
        firstName = fName;
        lastName = lName;
        email = userEmail;
    }

    // Getters to use in other controllers
    public static int getUserId() { return userId; }
    public static String getFirstName() { return firstName; }
    public static String getLastName() { return lastName; }
    public static String getEmail() { return email; }

    public static void setCustomerId(int id){ customerId = id;}
    public static int getCustomerId(){return customerId;}

    public static void setEmployeeId(int id){ employeeId = id;}
    public static int getEmployeeId(){return employeeId;}

    public static void cleanUserSession() {
        userId = 0;
        customerId = 0;
        employeeId = 0;
        firstName = null;
        lastName = null;
        email = null;
    }
}