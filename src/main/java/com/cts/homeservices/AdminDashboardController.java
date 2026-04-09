/** This class provides controls to the administrator dashboard.
 * It also adds interactive designs to UI elements, such as buttons and textboxes.
 *
 * @author Nicholas Brathwaite
 * @version 1.0
 *
 */


package com.cts.homeservices;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;
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

public class AdminDashboardController implements Initializable {
    static {
        Logger.getLogger(AdminDashboardController.class.getName());
    }

    //Class Parameters that connect admin-dashboard.fxml and this controller
    @FXML
    private Button btnManageBooking;

    @FXML
    private Button btnViewAccounts;

    @FXML
    private Button btnPrintReport;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Set up of the styles to be used for UI elements
        String btnNormal = "-fx-font: 20px\"Leelawadee\";-fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 20px\"Leelawadee\"; -fx-border-radius: 15; -fx-border-color:  #01402e; -fx-border-width:2; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";

        //Applying styles to the UI elements
        btnManageBooking.setStyle(btnNormal);
        btnManageBooking.setOnMouseEntered(mouseEvent -> btnManageBooking.setStyle(btnHover));
        btnManageBooking.setOnMouseExited(mouseEvent -> btnManageBooking.setStyle(btnNormal));

        btnViewAccounts.setStyle(btnNormal);
        btnViewAccounts.setOnMouseEntered(mouseEvent -> btnViewAccounts.setStyle(btnHover));
        btnViewAccounts.setOnMouseExited(mouseEvent -> btnViewAccounts.setStyle(btnNormal));


        btnPrintReport.setStyle(btnNormal);
        btnPrintReport.setOnMouseEntered(mouseEvent -> btnPrintReport.setStyle(btnHover));
        btnPrintReport.setOnMouseExited(mouseEvent -> btnPrintReport.setStyle(btnNormal));
    }

    //Allows for the administrator to log out and go back to log in screen
    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "login-view.fxml", 1100, 750);
    }

    //Opens the Manage Booking scene
    @FXML
    private void manageBookings(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "admin-manage-bookings.fxml", 1100, 750);
    }

    //Opens the View Accounts Scene
    @FXML
    private void viewAccounts(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "admin-view-accounts.fxml", 1100, 750);
    }

    //Opens the Print Reports Scene
    @FXML
    private void openPrintReport(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "admin-print-report.fxml", 1100, 750);
    }

}