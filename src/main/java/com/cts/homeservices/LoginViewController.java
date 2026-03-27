package com.cts.homeservices;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class LoginViewController implements Initializable {
    private static final Logger logger = Logger.getLogger(LoginViewController.class.getName());


    //Class Parameters that connect login-view.fxml and this controller
    @FXML
    private TextField tbLoginEmail;

    @FXML
    private TextField tbLoginPassword;

    @FXML
    private ComboBox<String> cBoxLoginAccType;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink btnRegisterLink;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populate ComboBox
        cBoxLoginAccType.setItems(FXCollections.observableArrayList("Customer", "Worker", "Admin"));

        //Setting up the styles to be used
        String defaultStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color: #cccccc; -fx-border-width:1; ";
        String focusedStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color:  #036248; -fx-border-width:2; ";
        String btnNormal = "-fx-background-color:  white; -fx-text-fill: #7c5050; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";

        //Text Field style on event
        tbLoginEmail.setStyle(defaultStyle);
        tbLoginEmail.focusedProperty().addListener((observableValue, oldVal, newVal) ->{
            if (newVal) tbLoginEmail.setStyle(focusedStyle);
            else tbLoginEmail.setStyle(defaultStyle);
        });

        tbLoginPassword.setStyle(defaultStyle);
        tbLoginPassword.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbLoginPassword.setStyle(focusedStyle);
            else tbLoginPassword.setStyle(defaultStyle);
        });
        //Combo Box style on event
        String comboFont = "-fx-font: 15px\"Ariel\";";
        cBoxLoginAccType.setStyle(defaultStyle + comboFont);
        cBoxLoginAccType.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) cBoxLoginAccType.setStyle(focusedStyle + comboFont);
            else cBoxLoginAccType.setStyle(defaultStyle + comboFont);
        });

        btnLogin.setStyle(btnNormal);
        btnLogin.setOnMouseEntered(mouseEvent -> btnLogin.setStyle(btnHover));
        btnLogin.setOnMouseExited(mouseEvent -> btnLogin.setStyle(btnNormal));

    }


    //Go to registration screen
    @FXML
    private void goToRegistration(javafx.event.ActionEvent event) throws IOException {
        try {
            // 1. Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registration-view.fxml"));

            // 2. Create the new scene with your preferred dimensions
            Scene scene = new Scene(fxmlLoader.load(), 1100, 750);

            // 3. Get the current stage from the click event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // 4. Set the new scene and show it
            stage.setScene(scene);
            stage.centerOnScreen(); // Optional: keeps the window centered
            stage.show();

        } catch (IOException e) {
            System.err.println("Error: Could not find registration-view.fxml");
            e.printStackTrace();
        }
    }
}
