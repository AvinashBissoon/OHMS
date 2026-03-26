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

public class OnlineHomeServiceApp extends Application {

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
            String query = "SELECT * FROM tblcustomer";
            dc.rst = dc.stat.executeQuery(query);

            while (dc.rst.next()) {
                System.out.print(dc.rst.getInt("customerid"));
                System.out.print("  ");
                System.out.print(dc.rst.getString("customerfirstname"));
                System.out.print("  ");
                System.out.print(dc.rst.getString("customermobilephone"));
                System.out.print("  ");
                System.out.println(dc.rst.getString("customeremail"));
            }
        } catch (SQLException e) {
            // Log the exception using the Java logger
            logger.severe("An error occurred: Database connectivity failed");
            logger.severe(e.toString());
            System.exit(0);
        }
    }

    public void changeScene(String fxml, Integer sWidth, Integer sHeight) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));

        switch (fxml) {
            case "login-view.fxml":
                currentStg.setTitle("Online Home Service Solutions: User Login");
                break;

            case "registration-view.fxml":
                currentStg.setTitle("Online Home Service Solutions: User Registration");
        }

    }

    public static void main(String[] args) {
        launch();
    }
}