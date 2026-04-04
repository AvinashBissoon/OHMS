package com.cts.homeservices;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AdminManageBookingsController implements Initializable {
    static {
        Logger.getLogger(AdminManageBookingsController.class.getName());
    }



    @FXML
    private TableView<Booking> tblViewBooking;

    @FXML
    private TableColumn<Booking, Integer> colBookingId;

    @FXML
    private TableColumn<Booking, String> colDate;

    @FXML
    private TableColumn<Booking, String> colTime;

    @FXML
    private TableColumn<Booking, String> colService;

    @FXML
    private TableColumn<Booking, String> colDetails;

    @FXML
    private TableColumn<Booking, String> colAssignedTo;

    @FXML
    private TableColumn<Booking, String> colStatus;

    @FXML
    private TextField tbBookingID1;

    @FXML
    private TextField tbBookingID2;

    @FXML
    private TextField tbService1;

    @FXML
    private TextField tbService2;

    @FXML
    private TextField tbAssignedEmployee;

    @FXML
    private Button btnAssignEmployee;

    @FXML
    private Button btnCancelBooking;

    @FXML
    private Button btnReturnToHome;

    @FXML
    private ObservableList<Booking> bookingList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting up the styles to be used
        String btnNormal = "-fx-font: 20px\"Leelawadee\";-fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 20px\"Leelawadee\"; -fx-border-radius: 15; -fx-border-color:  #01402e; -fx-border-width:2; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnNormalAccordian = "-fx-font: 14px\"Leelawadee\";-fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHoverAccordian = "-fx-font: 14px\"Leelawadee\"; -fx-border-radius: 15; -fx-border-color:  #01402e; -fx-border-width:2; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";
        String defaultStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color: #cccccc; -fx-border-width:1; ";
        String focusedStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color:  #036248; -fx-border-width:2; ";


        tbBookingID1.setStyle(defaultStyle);
        tbBookingID1.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbBookingID1.setStyle(focusedStyle);
            else tbBookingID1.setStyle(defaultStyle);
        });

        tbBookingID2.setStyle(defaultStyle);
        tbBookingID2.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbBookingID2.setStyle(focusedStyle);
            else tbBookingID2.setStyle(defaultStyle);
        });

        tbService1.setStyle(defaultStyle);
        tbService1.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbService1.setStyle(focusedStyle);
            else tbService1.setStyle(defaultStyle);
        });

        tbService2.setStyle(defaultStyle);
        tbService2.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbService2.setStyle(focusedStyle);
            else tbService2.setStyle(defaultStyle);
        });

        tbAssignedEmployee.setStyle(defaultStyle);
        tbAssignedEmployee.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbAssignedEmployee.setStyle(focusedStyle);
            else tbAssignedEmployee.setStyle(defaultStyle);
        });


        btnReturnToHome.setStyle(btnNormal);
        btnReturnToHome.setOnMouseEntered(mouseEvent -> btnReturnToHome.setStyle(btnHover));
        btnReturnToHome.setOnMouseExited(mouseEvent -> btnReturnToHome.setStyle(btnNormal));

        btnCancelBooking.setStyle(btnNormalAccordian);
        btnCancelBooking.setOnMouseEntered(mouseEvent -> btnCancelBooking.setStyle(btnHoverAccordian));
        btnCancelBooking.setOnMouseExited(mouseEvent -> btnCancelBooking.setStyle(btnNormalAccordian));

        btnAssignEmployee.setStyle(btnNormalAccordian);
        btnAssignEmployee.setOnMouseEntered(mouseEvent -> btnAssignEmployee.setStyle(btnHoverAccordian));
        btnAssignEmployee.setOnMouseExited(mouseEvent -> btnAssignEmployee.setStyle(btnNormalAccordian));


        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colService.setCellValueFactory(new PropertyValueFactory<>("service"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblViewBooking.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if (newSelection != null) {
                tbBookingID1.setText(String.valueOf(newSelection.getBookingId()));
                tbService1.setText(newSelection.getService());
                tbAssignedEmployee.setText(newSelection.getAssignedTo());

                //cancel fields
                tbBookingID2.setText(String.valueOf(newSelection.getBookingId()));
                tbService2.setText(newSelection.getService());
            }
        });

        loadData();
    }

    private void loadData(){
        bookingList.clear();
        DatabaseConnection dc = new DatabaseConnection();

        String query = "SELECT bookingid, booking_date, booking_time, servicetype, servicedetails, assigned_to, status FROM tblBooking";

        try {
            dc.ps = dc.con.prepareStatement(query);
            dc.rst = dc.ps.executeQuery();

            while (dc.rst.next()) {
                bookingList.add(new Booking(
                        dc.rst.getInt("bookingid"),
                        dc.rst.getString("booking_date"),
                        dc.rst.getString("booking_time"),
                        dc.rst.getString("servicetype"),
                        dc.rst.getString("servicedetails"),
                        dc.rst.getString("assigned_to"),
                        dc.rst.getString("status")
                ));
            }
            tblViewBooking.setItems(bookingList);
        } catch (Exception e){
        }
    }

    @FXML
    private void assignEmployee(ActionEvent event) {
        DatabaseConnection dc = new DatabaseConnection();

        String bookId = tbBookingID1.getText();
        String assignEmployee = tbAssignedEmployee.getText();

        String query = "UPDATE tblBooking SET assigned_to = ?, status = 'Assigned' WHERE bookingid = ?";

        try {
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, assignEmployee);
            dc.ps.setString(2, bookId);

            int rowsUpdated = dc.ps.executeUpdate();

            if (rowsUpdated > 0) {
                loadData();

                tbBookingID1.clear();
                tbBookingID2.clear();
                tbService1.clear();
                tbService2.clear();
                tbAssignedEmployee.clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Online Home Service Solution: Info Dialog");
                alert.setHeaderText("Booking Information");
                alert.setContentText(assignEmployee + " was successfully assigned to booking # " + bookId);
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Online Home Service Solution: Info Dialog");
                alert.setHeaderText("Booking Information: No booking selected");
                alert.setContentText("Please select a booking to Assign an Employee!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void cancelBooking (ActionEvent event) {
        Booking selectedBooking = tblViewBooking.getSelectionModel().getSelectedItem();

        if (selectedBooking != null) {
            try {
                DatabaseConnection dc = new DatabaseConnection();
                String query = "UPDATE tblbooking SET assigned_to = ?, status = ? WHERE bookingid = ?";

                dc.ps = dc.con.prepareStatement(query);
                dc.ps.setString(1, "N/A");
                dc.ps.setString(2, "Cancelled");
                dc.ps.setInt(3, selectedBooking.getBookingId());

                int rowUpdated = dc.ps.executeUpdate();
                if (rowUpdated > 0) {
                    loadData();

                    tbBookingID1.clear();
                    tbBookingID2.clear();
                    tbService1.clear();
                    tbService2.clear();
                    tbAssignedEmployee.clear();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Online Home Service Solution: Info Dialog");
                    alert.setHeaderText("Booking Information");
                    alert.setContentText("Customer booking was successfully cancelled!");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Online Home Service Solution: Info Dialog");
            alert.setHeaderText("Booking Information: No booking selected");
            alert.setContentText("Please select a booking to cancel!");
            alert.showAndWait();
        }

    }

    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "admin-dashboard.fxml", 1100, 750);
    }
}