package com.cts.homeservices;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class RegistrationViewController implements Initializable {
    private static final Logger logger = Logger.getLogger(RegistrationViewController.class.getName());

    @FXML
    private Button btnBackToLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void goToLogin(javafx.event.ActionEvent event) throws IOException{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 750);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            System.err.println("Error: Could not register login-view.fxml");
            e.printStackTrace();
        }
    }
}
