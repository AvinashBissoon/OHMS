/** This class provides controls for a customer to view and cancel a booking they have placed.
 * It also adds interactive designs to UI elements, such as buttons and textboxes.
 *
 * @author Avinash Bissoon
 * @version 1.0
 *
 */


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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class CustomerHistoryController implements Initializable {
    private static final Logger logger = Logger.getLogger(CustomerHistoryController.class.getName());

    //Class Parameters that connect customer-view-history.fxml and this controller
    @FXML
    private TableView<Booking> tableBookings;

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
    private ObservableList<Booking> bookingList = FXCollections.observableArrayList();

    @FXML
    private Button btnReturnToHome;

    @FXML
    private Button btnCancelBooking;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Set up of the styles to be used for UI elements
        String btnNormal = "-fx-font: 18px\"Leelawadee\"; -fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 18px\"Leelawadee\"; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";


        //Applying styles to the UI elements
        btnReturnToHome.setStyle(btnNormal);
        btnReturnToHome.setOnMouseEntered(mouseEvent -> btnReturnToHome.setStyle(btnHover));
        btnReturnToHome.setOnMouseExited(mouseEvent -> btnReturnToHome.setStyle(btnNormal));

        btnCancelBooking.setStyle(btnNormal);
        btnCancelBooking.setOnMouseEntered(mouseEvent -> btnCancelBooking.setStyle(btnHover));
        btnCancelBooking.setOnMouseExited(mouseEvent -> btnCancelBooking.setStyle(btnNormal));

        //Mapping Columns to Booking class
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colService.setCellValueFactory(new PropertyValueFactory<>("service"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadData();
    }

    //Loads booking data into the respective columns
    private void loadData(){
        bookingList.clear();
        DatabaseConnection dc = new DatabaseConnection();

        String query = "SELECT bookingid, booking_date, booking_time, servicetype, servicedetails, assigned_to, status FROM tblBooking WHERE userid = ?";

        try{
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setInt(1, UserSession.getUserId());
            dc.rst = dc.ps.executeQuery();

            while (dc.rst.next()){
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

            tableBookings.setItems(bookingList);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    //Allows for booking to be canceled by customer
    @FXML
    private void cancelBooking (ActionEvent event) {
        Booking selectedBooking = tableBookings.getSelectionModel().getSelectedItem();

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
                    selectedBooking.setAssignedTo("N/A");
                    selectedBooking.setStatus("Cancelled");
                    tableBookings.refresh();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Online Home Service Solution: Info Dialog");
                    alert.setHeaderText("Booking Information");
                    alert.setContentText("Your booking was successfully cancelled!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Online Home Service Solution: Info Dialog");
            alert.setHeaderText("Booking Information: No booking selected");
            alert.setContentText("Please select a booking to cancel");
            alert.showAndWait();
        }
    }

    //Takes the customer back to the Customer Dashboard
    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "customer-dashboard.fxml", 1100, 750);
    }
}