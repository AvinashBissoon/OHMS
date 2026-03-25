package com.cts.homeservices;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

//import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.SQLException;

public class JavaFxDemoController implements Initializable {
    //Class parameters that will connect the login-view.fxml scene to this Controller
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnReset;

    @FXML
    private PasswordField tBoxPassword;

    @FXML
    private TextField tBoxUsername;

    @FXML
    private ComboBox<String> cBoxRole;

    @FXML
    private Label lblWrongLogin;

    //Populate ComboBox
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cBoxRole.setItems(FXCollections.observableArrayList("Clerk", "Manager", "Supervisor"));
        cBoxRole.setStyle("-fx-font: 20px \"Ariel\";");
        Hyperlink lblRegister = new Hyperlink("Not Registered... Click here to Register!");
    }

    //Login Button click event
    @FXML
    void userLogin(ActionEvent event) throws IOException, SQLException {
        checkLogin();
    }

    @FXML
    private void checkLogin() throws IOException, SQLException {
        OnlineHomeServiceApp d = new OnlineHomeServiceApp();
        String s1 = tBoxUsername.getText();
        String s2 = tBoxPassword.getText();
        String s3 = cBoxRole.getValue();
        String s4;
        String s5;
        String s6;

        //Validate user credentials
        DatabaseConnection dc = new DatabaseConnection();

        String query = "SELECT username, password, userRole, active FROM tblUser WHERE username = ? AND password = ? and userRole = ? and active = 1";
        dc.ps = dc.con.prepareStatement(query);
        dc.ps.setString(1, s1);
        dc.ps.setString(2, s2);
        dc.ps.setString(3, s3);

        dc.rst = dc.ps.executeQuery();

        if (dc.rst.next()) {
            s4 = dc.rst.getString(1);
            s5 = dc.rst.getString(2);
            s6 = dc.rst.getString(3);

            if (s4.equals(s1) && s5.equals(s2) && s6.equals(s3)) {
                lblWrongLogin.setText("Credentials Authenticated");
                d.changeScene("dashboard-view.fxml", 1200, 850);
            }
        } else if (s1.isEmpty() && s2.isEmpty() && s3 == null) {
            lblWrongLogin.setTextFill(Color.BLUE);  // Success case
            lblWrongLogin.setText("Please enter valid credentials.");
        } else {
            lblWrongLogin.setTextFill(Color.RED);   // Failure case
            lblWrongLogin.setText("Wrong username or password!");
        }
    }

    //Reset Button click event
    @FXML
    private void reset() throws IOException {
        try {
            tBoxUsername.setText("");
            tBoxPassword.setText("");
            cBoxRole.setValue("select user role");
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    //Register Button click event
    @FXML
    void registerUser(ActionEvent event) throws IOException {
        try {
            OnlineHomeServiceApp d = new OnlineHomeServiceApp();
            d.changeScene("register-user-view.fxml", 1100, 750);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
