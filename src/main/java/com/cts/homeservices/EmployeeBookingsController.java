package com.cts.homeservices;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.PrivateKey;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class EmployeeBookingsController implements Initializable {
    static {
        Logger.getLogger(EmployeeBookingsController.class.getName());
    }



    @FXML
    private TableView<EmployeeBooking> tblViewBooking;

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
    private TableColumn<Booking, String> colAddress;

    @FXML
    private TableColumn<Booking, String> colCity;

    @FXML
    private TableColumn<Booking, String> colAssignedTo;

    @FXML
    private TableColumn<Booking, String> colStatus;

    @FXML
    private Button btnInProgress;

    @FXML
    private Button btnCompleted;

    @FXML
    private Button btnReturnToHome;

    @FXML
    private ObservableList<EmployeeBooking> bookingList = FXCollections.observableArrayList();


    //Populate Country and Role Combo Boxes
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Setting up the styles to be used
        String btnNormal = "-fx-font: 18px\"Leelawadee\"; -fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 18px\"Leelawadee\"; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";

        btnInProgress.setStyle(btnNormal);
        btnInProgress.setOnMouseEntered(mouseEvent -> btnInProgress.setStyle(btnHover));
        btnInProgress.setOnMouseExited(mouseEvent -> btnInProgress.setStyle(btnNormal));

        btnCompleted.setStyle(btnNormal);
        btnCompleted.setOnMouseEntered(mouseEvent -> btnCompleted.setStyle(btnHover));
        btnCompleted.setOnMouseExited(mouseEvent -> btnCompleted.setStyle(btnNormal));

        btnReturnToHome.setStyle(btnNormal);
        btnReturnToHome.setOnMouseEntered(mouseEvent -> btnReturnToHome.setStyle(btnHover));
        btnReturnToHome.setOnMouseExited(mouseEvent -> btnReturnToHome.setStyle(btnNormal));


        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colService.setCellValueFactory(new PropertyValueFactory<>("service"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadData();
    }

    private void loadData(){
        bookingList.clear();
        DatabaseConnection dc = new DatabaseConnection();

        String query = "SELECT * FROM tblBooking";

        try {
            dc.ps = dc.con.prepareStatement(query);
            dc.rst = dc.ps.executeQuery();

            while (dc.rst.next()) {
                bookingList.add(new EmployeeBooking(
                        dc.rst.getInt("bookingid"),
                        dc.rst.getString("booking_date"),
                        dc.rst.getString("booking_time"),
                        dc.rst.getString("servicetype"),
                        dc.rst.getString("servicedetails"),
                        dc.rst.getString("address"),
                        dc.rst.getString("city"),
                        dc.rst.getString("assigned_to"),
                        dc.rst.getString("status")
                ));
            }
            tblViewBooking.setItems(bookingList);
        } catch (Exception e){
        }
    }

    @FXML
    private void inProgress(ActionEvent event){
        EmployeeBooking selectedBooking = tblViewBooking.getSelectionModel().getSelectedItem();

        if (selectedBooking != null){
            try {
                DatabaseConnection dc = new DatabaseConnection();
                String query = "UPDATE tblbooking SET status = ? WHERE bookingid = ?";

                dc.ps = dc.con.prepareStatement(query);
                dc.ps.setString(1, "In Progress");
                dc.ps.setInt(2, selectedBooking.getBookingId());

                int rowUpdated = dc.ps.executeUpdate();
                if (rowUpdated > 0) {
                    loadData();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Online Home Service Solution: Info Dialog");
                    alert.setHeaderText("Booking Status Information");
                    alert.setContentText("Booking status was updated to In Progress!");
                    alert.showAndWait();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void completed(ActionEvent event){
        EmployeeBooking selectedBooking = tblViewBooking.getSelectionModel().getSelectedItem();

        if (selectedBooking != null){
            try {
                DatabaseConnection dc = new DatabaseConnection();
                String query = "UPDATE tblbooking SET status = ? WHERE bookingid = ?";

                dc.ps = dc.con.prepareStatement(query);
                dc.ps.setString(1, "Completed");
                dc.ps.setInt(2, selectedBooking.getBookingId());

                int rowUpdated = dc.ps.executeUpdate();
                if (rowUpdated > 0) {
                    loadData();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Online Home Service Solution: Info Dialog");
                    alert.setHeaderText("Booking Status Information");
                    alert.setContentText("Booking status was updated to Completed!");
                    alert.showAndWait();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "employee-dashboard.fxml", 1100, 750);
    }
}