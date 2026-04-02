package com.cts.homeservices;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class LoginViewController implements Initializable {
    private static final Logger logger = Logger.getLogger(LoginViewController.class.getName());


    //Class Parameters that connect login-view.fxml and this controller
    @FXML
    private TextField tbLoginEmail;

    @FXML
    private TextField tbLoginPassword;

    @FXML
    private ComboBox<String> cBoxLoginAccType;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink btnRegisterLink;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populate ComboBox
        cBoxLoginAccType.setItems(FXCollections.observableArrayList("Customer", "Employee", "Admin"));

        //Setting up the styles to be used
        String defaultStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color: #cccccc; -fx-border-width:1; ";
        String focusedStyle = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-radius: 15; -fx-border-color:  #036248; -fx-border-width:2; ";
        String btnNormal = "-fx-font: 18px\"Leelawadee\"; -fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 18px\"Leelawadee\"; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";

        //Text Field style on event
        tbLoginEmail.setStyle(defaultStyle);
        tbLoginEmail.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbLoginEmail.setStyle(focusedStyle);
            else tbLoginEmail.setStyle(defaultStyle);
        });

        tbLoginPassword.setStyle(defaultStyle);
        tbLoginPassword.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) tbLoginPassword.setStyle(focusedStyle);
            else tbLoginPassword.setStyle(defaultStyle);
        });
        //Combo Box style on event
        String comboFont = "-fx-font: 15px\"Ariel\";";
        cBoxLoginAccType.setStyle(defaultStyle + comboFont);
        cBoxLoginAccType.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) cBoxLoginAccType.setStyle(focusedStyle + comboFont);
            else cBoxLoginAccType.setStyle(defaultStyle + comboFont);
        });

        btnLogin.setStyle(btnNormal);
        btnLogin.setOnMouseEntered(mouseEvent -> btnLogin.setStyle(btnHover));
        btnLogin.setOnMouseExited(mouseEvent -> btnLogin.setStyle(btnNormal));

    }


    //Go to registration screen
    @FXML
    private void goToRegistration(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "registration-view.fxml", 1100, 750);

    }

    @FXML
    void login(ActionEvent event) throws IOException, SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        String email = tbLoginEmail.getText();
        String password = tbLoginPassword.getText();
        String accountType = cBoxLoginAccType.getValue();

        if (accountType == null || email.isEmpty() || password.isEmpty()) {
            return;
        }

        String query = "SELECT * FROM tblUsers WHERE email = ? AND password = ? AND accountType = ?";
        try {
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, email);
            dc.ps.setString(2, password);
            dc.ps.setString(3, accountType);

            dc.rst = dc.ps.executeQuery();
            if (dc.rst.next()) {

                int id = dc.rst.getInt("userid");
                String fName = dc.rst.getString("firstname");
                String lName = dc.rst.getString("lastname");
                String uEmail = dc.rst.getString("email");

                UserSession.init(id, fName, lName, uEmail);

                if ("Customer".equals(accountType)) {
                    String customerQuery = "SELECT customerid FROM tblcustomer WHERE email = ?";
                    dc.ps = dc.con.prepareStatement(customerQuery);
                    dc.ps.setString(1, uEmail);
                    dc.rst = dc.ps.executeQuery();
                    if (dc.rst.next()) {
                        int customerId = dc.rst.getInt("customerid");
                        UserSession.setCustomerId(customerId);
                    }
                }
                if ("Employee".equals(accountType)) {
                    String employeeQuery = "SELECT employeeid FROM tblemployee WHERE email = ?";
                    dc.ps = dc.con.prepareStatement(employeeQuery);
                    dc.ps.setString(1, uEmail);
                    dc.rst = dc.ps.executeQuery();
                    if (dc.rst.next()) {
                        int employeeId = dc.rst.getInt("employeeid");
                        UserSession.setEmployeeId(employeeId);
                    }
                }


                System.out.println("Session started for: " + UserSession.getFirstName());

                String fxmlFile = "";
                switch (accountType) {
                    case "Customer":
                        fxmlFile = "customer-dashboard.fxml";
                        break;

                    case "Employee":
                        fxmlFile = "employee-dashboard.fxml";
                        break;

                    case "Admin":
                        fxmlFile = "admin-dashboard.fxml";
                        break;
                }

                Stage currentStage = (Stage) tbLoginEmail.getScene().getWindow();
                OnlineHomeServiceApp.changeScene(currentStage, fxmlFile, 1100, 750);
            } else {
                System.out.println("Invalid Login Credentials.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


