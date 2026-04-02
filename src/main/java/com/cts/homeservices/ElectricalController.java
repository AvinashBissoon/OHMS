package com.cts.homeservices;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ElectricalController implements Initializable {
    private static final Logger logger = Logger.getLogger(ElectricalController.class.getName());


    //Class Parameters that connect register-view.fxml to this controller
    @FXML
    private ComboBox<String> cBoxService;

    @FXML
    private TextArea tAreaDetails;

    @FXML
    private TextField tbAddress;

    @FXML
    private TextField tbCity;

    @FXML
    private DatePicker date;

    @FXML
    private TextField tbTime;

    @FXML
    private Button btnReturnToHome;

    @FXML
    private Button btnBookNow;


    //Populate Country and Role Combo Boxes
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cBoxService.setItems(FXCollections.observableArrayList("Electrical: Light Fixture Installation", "Electrical: Outlet and Switch Repair", "Electrical: Smart Home Integration", "Electrical: Safety Inspection", "Electrical: Other"));
        cBoxService.setStyle("-fx-font: 15px\"Ariel\";");


        //Setting up the styles to be used
        String defaultStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color: #cccccc; -fx-border-width:1; ";
        String focusedStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color:  #036248; -fx-border-width:2; ";

        String btnNormal = "-fx-font: 18px\"Leelawadee\"; -fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 18px\"Leelawadee\"; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";

        String defStyle = "-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width:1; ";
        String focusStyle = "-fx-background-color: white; -fx-border-color:  #036248; -fx-border-width:2; ";

        //Text Field style on event
        tAreaDetails.setStyle(defStyle);
        tAreaDetails.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tAreaDetails.setStyle(focusStyle);
            else tAreaDetails.setStyle(defStyle);
        });

        tbAddress.setStyle(defaultStyle);
        tbAddress.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbAddress.setStyle(focusedStyle);
            else tbAddress.setStyle(defaultStyle);
        });

        tbCity.setStyle(defaultStyle);
        tbCity.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbCity.setStyle(focusedStyle);
            else tbCity.setStyle(defaultStyle);
        });

        date.setStyle(defStyle);
        date.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) date.setStyle(focusStyle);
            else date.setStyle(defStyle);
        });

        tbTime.setStyle(defaultStyle);
        tbTime.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbTime.setStyle(focusedStyle);
            else tbTime.setStyle(defaultStyle);
        });

        String comboFont = "-fx-font: 15px\"Ariel\";";
        cBoxService.setStyle(defaultStyle + comboFont);
        cBoxService.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) cBoxService.setStyle(focusedStyle + comboFont);
            else cBoxService.setStyle(defaultStyle + comboFont);
        });

        btnReturnToHome.setStyle(btnNormal);
        btnReturnToHome.setOnMouseEntered(mouseEvent -> btnReturnToHome.setStyle(btnHover));
        btnReturnToHome.setOnMouseExited(mouseEvent -> btnReturnToHome.setStyle(btnNormal));

        btnBookNow.setStyle(btnNormal);
        btnBookNow.setOnMouseEntered(mouseEvent -> btnBookNow.setStyle(btnHover));
        btnBookNow.setOnMouseExited(mouseEvent -> btnBookNow.setStyle(btnNormal));


    }

    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "customer-dashboard.fxml", 1100, 750);
    }

    @FXML
    private void book(ActionEvent event) throws IOException {
        DatabaseConnection dc = new DatabaseConnection();

        String query = "INSERT INTO tblBooking (userid, servicetype, servicedetails, address, city, booking_date, booking_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            dc.ps = dc.con.prepareStatement(query);

            dc.ps.setInt(1,UserSession.getUserId());

            dc.ps.setString(2, cBoxService.getValue());
            dc.ps.setString(3, tAreaDetails.getText());
            dc.ps.setString(4, tbAddress.getText());
            dc.ps.setString(5, tbCity.getText());

            if (date.getValue() != null){
                dc.ps.setDate(6, java.sql.Date.valueOf(date.getValue()));
            }

            String timeString = tbTime.getText();
            dc.ps.setTime(7, java.sql.Time.valueOf(timeString + ":00"));

            dc.ps.executeUpdate();
            System.out.println("Booking successful for: " + UserSession.getFirstName());
        } catch (Exception e){
            System.out.println("Booking Failed!");
            e.printStackTrace();
        }

    }
}