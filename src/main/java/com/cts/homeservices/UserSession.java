package com.cts.homeservices;

public class UserSession {
    private static int userId;
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

    public static void cleanUserSession() {
        userId = 0;
        firstName = null;
        lastName = null;
        email = null;
    }
}