package com.cts.homeservices;

import javafx.collections.FXCollections;
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

public class RegistrationViewController implements Initializable {
    private static final Logger logger = Logger.getLogger(RegistrationViewController.class.getName());


    //Class Parameters that connect register-view.fxml to this controller
    @FXML
    private TextField tbFirstName;

    @FXML
    private TextField tbLastName;

    @FXML
    private TextField tbAddressLine1;

    @FXML
    private TextField tbAddressLine2;

    @FXML
    private TextField tbCity;

    @FXML
    private ComboBox<String> cBoxCountry;

    @FXML
    private TextField tbMobileNumber;

    @FXML
    private TextField tbRegisterEmail;

    @FXML
    PasswordField tbRegisterPassword;

    @FXML
    private ComboBox<String> cBoxRegisterAccType;

    @FXML
    private Button btnBackToLogin;



    //Populate Country and Role Combo Boxes
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cBoxCountry.setItems(FXCollections.observableArrayList("Trinidad", "Tobago"));
        cBoxCountry.setStyle("-fx-font: 15px\"Ariel\";");
        cBoxRegisterAccType.setItems(FXCollections.observableArrayList("Customer", "Employee", "Admin"));
        cBoxRegisterAccType.setStyle("-fx-font: 15px\"Ariel\";");
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

    //Register User on "Register" Button Click
    @FXML
    void register() throws IOException, SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        String accountType = cBoxRegisterAccType.getValue();
        if (accountType == null)
            return;

        String query = "";
        try {
            switch (accountType) {
                case "Customer":
                    query = "INSERT INTO tblCustomer VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    break;
                case "Admin":
                    query = "INSERT INTO tblAdmin VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    break;
                case "Employee":
                    query = "INSERT INTO tblEmployee VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    break;
            }
            //Build a protected query to add to customer

            //Assign data to protected parameters for registration

            dc.ps = dc.con.prepareStatement(query);

            String s1 = tbFirstName.getText();
            dc.ps.setString(1,s1);

            String s2 = tbLastName.getText();
            dc.ps.setString(2,s2);

            String s3 = tbAddressLine1.getText();
            dc.ps.setString(3,s3);

            String s4 = tbAddressLine2.getText();
            dc.ps.setString(4,s4);

            String s5 = tbCity.getText();
            dc.ps.setString(5,s5);

            String s6 = cBoxCountry.getValue();
            dc.ps.setString(6,s6);

            String s7 = tbMobileNumber.getText();
            dc.ps.setString(7,s7);

            String s8 = tbRegisterEmail.getText();
            dc.ps.setString(8,s8);

            String s9 = tbRegisterPassword.getText();
            dc.ps.setString(9,s9);

            String s10 = cBoxRegisterAccType.getValue();
            dc.ps.setString(10,s10);

            dc.ps.setInt(11,1);

            if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty() && s4.isEmpty() && s5.isEmpty() && s6.isEmpty() && s7.isEmpty() && s8.isEmpty() && s9.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Online Home Service Solution: Info Dialog");
                alert.setHeaderText("Input Validation");
                alert.setContentText("Please ensure ALL fields are populated on the Registration Form!");
                alert.showAndWait();
            } else {
                //Execute the query on the Customer Table, if successful
                int success = dc.ps.executeUpdate();
                if (success>0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Online Home Service Solution: Info Dialog");
                    alert.setHeaderText("Registration Successful");
                    alert.setContentText("Account was successfully created! You can now return to login page.");
                    alert.showAndWait();
                }
            }

            //Build a protected query to retrieve specified

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
