package com.cts.homeservices;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class EmployeeDashboardController implements Initializable {
    static {
        Logger.getLogger(EmployeeDashboardController.class.getName());
    }


    //Class Parameters that connect customer-dashboard.fxml and this controller
    @FXML
    private Button btnBooking;

    @FXML
    private Button btnEditProfile;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting up the styles to be used
        String btnNormal = "-fx-font: 20px\"Leelawadee\";-fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 20px\"Leelawadee\"; -fx-border-radius: 15; -fx-border-color:  #01402e; -fx-border-width:2; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";


        btnBooking.setStyle(btnNormal);
        btnBooking.setOnMouseEntered(mouseEvent -> btnBooking.setStyle(btnHover));
        btnBooking.setOnMouseExited(mouseEvent -> btnBooking.setStyle(btnNormal));

        btnEditProfile.setStyle(btnNormal);
        btnEditProfile.setOnMouseEntered(mouseEvent -> btnEditProfile.setStyle(btnHover));
        btnEditProfile.setOnMouseExited(mouseEvent -> btnEditProfile.setStyle(btnNormal));

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "login-view.fxml", 1100, 750);
    }

    @FXML
    private void openEditProfile(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "employee-edit-profile.fxml", 1100, 750);
    }

    @FXML
    private void openBooking(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "employee-assigned-bookings.fxml", 1100, 750);
    }
}