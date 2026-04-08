/** This class allows for the account information to be saved to a "session" when a user has logged in.
 *
 * @author Avinash Bissoon
 * @version 1.0
 *
 */

package com.cts.homeservices;

public class UserSession {
    private static int userId;
    private static int customerId;
    private static int employeeId;
    private static String firstName;
    private static String lastName;
    private static String email;

    public static void init(int id, String fName, String lName, String userEmail) {
        userId = id;
        firstName = fName;
        lastName = lName;
        email = userEmail;
    }

    // Getters to use in controllers
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