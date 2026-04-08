package com.cts.homeservices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

public class  OnlineHomeServiceApp extends Application {

    private static final Logger logger = Logger.getLogger(OnlineHomeServiceApp.class.getName());

    DatabaseConnection dc = new DatabaseConnection();
    private static Stage currentStg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        currentStg = primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(OnlineHomeServiceApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Online Home Service Solutions: User Login");
        primaryStage.setWidth(1100);
        primaryStage.setHeight(750);
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            String query = "SELECT * FROM tblUsers";
            dc.rst = dc.stat.executeQuery(query);

            while (dc.rst.next()) {
                System.out.print(dc.rst.getString("Email"));
                System.out.print("  ");
                System.out.print(dc.rst.getString("Password"));
                System.out.print("  ");
                System.out.println(dc.rst.getString("AccountType"));
            }
        } catch (SQLException e) {
            // Log the exception using the Java logger
            logger.severe("An error occurred: Database connectivity failed");
            logger.severe(e.toString());
            System.exit(0);
        }
    }

    public static void changeScene(Stage currentStg, String fxml, Integer sWidth, Integer sHeight) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(OnlineHomeServiceApp.class.getResource(fxml)));

        Scene scene = new Scene(pane, sWidth, sHeight);
        currentStg.setScene(scene);
        currentStg.show();

        switch (fxml) {
            case "login-view.fxml":
                currentStg.setTitle("Online Home Service Solutions: User Login");
                break;

            case "registration-view.fxml":
                currentStg.setTitle("Online Home Service Solutions: User Registration");
                break;

            case "customer-dashboard.fxml":
                currentStg.setTitle("Online Home Service Solutions: Customer Dashboard");
                break;

            case "employee-dashboard.fxml":
                currentStg.setTitle("Online Home Service Solutions: Employee Dashboard");
                break;

            case "admin-dashboard.fxml":
                currentStg.setTitle("Online Home Service Solutions: Admin Dashboard");
                break;

            case "admin-manage-bookings.fxml":
                currentStg.setTitle("Online Home Service Solutions: Manage Bookings");
                break;

            case "admin-view-accounts.fxml":
                currentStg.setTitle("Online Home Service Solutions: View Accounts");
                break;

            case "housekeeping-view.fxml":
                currentStg.setTitle("Online Home Service Solutions: Housekeeping Booking");
                break;

            case "electrical-view.fxml":
                currentStg.setTitle("Online Home Service Solutions: Electrical Booking");
                break;

            case "landscaping-view.fxml":
                currentStg.setTitle("Online Home Service Solutions: Landscaping Booking");
                break;

            case "plumbing-view.fxml":
                currentStg.setTitle("Online Home Service Solutions: Plumbing Booking");
                break;

            case "customer-view-history.fxml":
                currentStg.setTitle("Online Home Service Solutions: Customer History");
                break;

            case "customer-edit-profile.fxml":
                currentStg.setTitle("Online Home Service Solutions: Edit Account");
                break;

            case "employee-edit-profile.fxml":
                currentStg.setTitle("Online Home Service Solutions: Employee Edit Account");
                break;
        }

    }

    public static void main(String[] args) {
        launch();
    }
}