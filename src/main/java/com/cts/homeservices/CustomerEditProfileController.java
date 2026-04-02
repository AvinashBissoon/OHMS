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

import static com.cts.homeservices.UserSession.getUserId;

public class CustomerEditProfileController implements Initializable {
    private static final Logger logger = Logger.getLogger(CustomerEditProfileController.class.getName());


    //Class Parameters
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
    private TextField tbCustomerEmail;

    @FXML
    PasswordField tbCustomerPassword;

    @FXML
    private Button btnReturnToHome;

    @FXML
    private Button btnSave;


    //Populate Country and Role Combo Boxes
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cBoxCountry.setItems(FXCollections.observableArrayList("Trinidad", "Tobago"));
        cBoxCountry.setStyle("-fx-font: 15px\"Ariel\";");


        //Setting up the styles to be used
        String defaultStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color: #cccccc; -fx-border-width:1; ";
        String focusedStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color:  #036248; -fx-border-width:2; ";
        String btnNormal = "-fx-font: 18px\"Leelawadee\"; -fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 18px\"Leelawadee\"; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";

        //Text Field style on event
        tbFirstName.setStyle(defaultStyle);
        tbFirstName.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbFirstName.setStyle(focusedStyle);
            else tbFirstName.setStyle(defaultStyle);
        });

        tbLastName.setStyle(defaultStyle);
        tbLastName.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbLastName.setStyle(focusedStyle);
            else tbLastName.setStyle(defaultStyle);
        });

        tbAddressLine1.setStyle(defaultStyle);
        tbAddressLine1.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbAddressLine1.setStyle(focusedStyle);
            else tbAddressLine1.setStyle(defaultStyle);
        });

        tbAddressLine2.setStyle(defaultStyle);
        tbAddressLine2.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbAddressLine2.setStyle(focusedStyle);
            else tbAddressLine2.setStyle(defaultStyle);
        });

        tbCity.setStyle(defaultStyle);
        tbCity.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbCity.setStyle(focusedStyle);
            else tbCity.setStyle(defaultStyle);
        });

        tbMobileNumber.setStyle(defaultStyle);
        tbMobileNumber.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbMobileNumber.setStyle(focusedStyle);
            else tbMobileNumber.setStyle(defaultStyle);
        });

        tbCustomerEmail.setStyle(defaultStyle);
        tbCustomerEmail.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbCustomerEmail.setStyle(focusedStyle);
            else tbCustomerEmail.setStyle(defaultStyle);
        });

        tbCustomerPassword.setStyle(defaultStyle);
        tbCustomerPassword.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbCustomerPassword.setStyle(focusedStyle);
            else tbCustomerPassword.setStyle(defaultStyle);
        });

        //Combo Box style on event
        String comboFont = "-fx-font: 15px\"Ariel\";";
        cBoxCountry.setStyle(defaultStyle + comboFont);
        cBoxCountry.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) cBoxCountry.setStyle(focusedStyle + comboFont);
            else cBoxCountry.setStyle(defaultStyle + comboFont);
        });

        btnReturnToHome.setStyle(btnNormal);
        btnReturnToHome.setOnMouseEntered(mouseEvent -> btnReturnToHome.setStyle(btnHover));
        btnReturnToHome.setOnMouseExited(mouseEvent -> btnReturnToHome.setStyle(btnNormal));

        btnSave.setStyle(btnNormal);
        btnSave.setOnMouseEntered(mouseEvent -> btnSave.setStyle(btnHover));
        btnSave.setOnMouseExited(mouseEvent -> btnSave.setStyle(btnNormal));

        loadUserData();
    }

    private void loadUserData(){
        System.out.println("DEBUG: UserSession ID is: " + getUserId());
        DatabaseConnection dc = new DatabaseConnection();
        String query = "SELECT * FROM tblcustomer WHERE customerid = ?";

        try {
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setInt(1, UserSession.getCustomerId());
            dc.rst = dc.ps.executeQuery();

            if (dc.rst.next()) {
                tbFirstName.setText(dc.rst.getString("firstname"));
                tbLastName.setText(dc.rst.getString("lastname"));
                tbAddressLine1.setText(dc.rst.getString("streetaddress1"));
                tbAddressLine2.setText(dc.rst.getString("streetaddress2"));
                tbCity.setText(dc.rst.getString("city"));
                cBoxCountry.setValue(dc.rst.getString("country"));
                tbMobileNumber.setText(dc.rst.getString("mobilephone"));
                tbCustomerEmail.setText(dc.rst.getString("email"));
                tbCustomerPassword.setText(dc.rst.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "customer-dashboard.fxml", 1100, 750);
    }

    @FXML
    private void saveEdits(ActionEvent event) {
        String query = "UPDATE tblcustomer SET firstname = ?, lastname = ?, streetaddress1 = ?, streetaddress2 = ?, city = ?, country = ?, mobilephone = ?, email = ?, password = ? WHERE customerid = ?";

        try {
            DatabaseConnection dc = new DatabaseConnection();
            dc.ps = dc.con.prepareStatement(query);

            dc.ps.setString(1, tbFirstName.getText());
            dc.ps.setString(2, tbLastName.getText());
            dc.ps.setString(3, tbAddressLine1.getText());
            dc.ps.setString(4, tbAddressLine2.getText());
            dc.ps.setString(5, tbCity.getText());
            dc.ps.setString(6, cBoxCountry.getValue());
            dc.ps.setString(7, tbMobileNumber.getText());
            dc.ps.setString(8, tbCustomerEmail.getText());
            dc.ps.setString(9, tbCustomerPassword.getText());
            dc.ps.setInt(10, UserSession.getCustomerId());

            int rowsUpdated = dc.ps.executeUpdate();
            if (rowsUpdated >0){
                System.out.println("Customer Profile Updated!");

                UserSession.init(UserSession.getUserId(),
                        tbFirstName.getText(),
                        tbLastName.getText(),
                        tbCustomerEmail.getText());

                UserSession.setCustomerId(UserSession.getCustomerId());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Online Home Service Solution: Info Dialog");
                alert.setHeaderText("Account Edits Successful");
                alert.setContentText("Account changes were successfully updated!");
                alert.showAndWait();

            } else {
                System.out.println("No Update saved!");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Online Home Service Solution: Info Dialog");
                alert.setHeaderText("Account Edit Error");
                alert.setContentText("Account changes were not saved! Please try again.");
                alert.showAndWait();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}