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

    @FXML
    private TableView<Booking> tableBookings;

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


    //Populate Country and Role Combo Boxes
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Setting up the styles to be used

        String btnNormal = "-fx-font: 18px\"Leelawadee\"; -fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 18px\"Leelawadee\"; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";



        btnReturnToHome.setStyle(btnNormal);
        btnReturnToHome.setOnMouseEntered(mouseEvent -> btnReturnToHome.setStyle(btnHover));
        btnReturnToHome.setOnMouseExited(mouseEvent -> btnReturnToHome.setStyle(btnNormal));

        btnCancelBooking.setStyle(btnNormal);
        btnCancelBooking.setOnMouseEntered(mouseEvent -> btnCancelBooking.setStyle(btnHover));
        btnCancelBooking.setOnMouseExited(mouseEvent -> btnCancelBooking.setStyle(btnNormal));

        //Mapping Columns to booking class

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colService.setCellValueFactory(new PropertyValueFactory<>("service"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadData();
    }

    private void loadData(){
        bookingList.clear();
        DatabaseConnection dc = new DatabaseConnection();

        String query = "SELECT booking_date, booking_time, servicetype, servicedetails, assigned_to, status " + "FROM tblBooking WHERE userid = ?";

        try{
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setInt(1, UserSession.getUserId());
            dc.rst = dc.ps.executeQuery();

            while (dc.rst.next()){
                bookingList.add(new Booking(
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

    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "customer-dashboard.fxml", 1100, 750);
    }

}