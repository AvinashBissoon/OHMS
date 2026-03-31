package com.cts.homeservices;


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

public class CustomerDashboardController implements Initializable {
    static {
        Logger.getLogger(CustomerDashboardController.class.getName());
    }


    //Class Parameters that connect customer-dashboard.fxml and this controller
    @FXML
    private Button btnHousekeeping;

    @FXML
    private Button btnElectrical ;

    @FXML
    private Button btnLandscaping;

    @FXML
    private Button btnPlumbing;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnLogOut;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting up the styles to be used
        String btnNormal = "-fx-font: 20px\"Leelawadee\";-fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 20px\"Leelawadee\"; -fx-border-radius: 15; -fx-border-color:  #01402e; -fx-border-width:2; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";


        btnHousekeeping.setStyle(btnNormal);
        btnHousekeeping.setOnMouseEntered(mouseEvent -> btnHousekeeping.setStyle(btnHover));
        btnHousekeeping.setOnMouseExited(mouseEvent -> btnHousekeeping.setStyle(btnNormal));

        btnElectrical.setStyle(btnNormal);
        btnElectrical.setOnMouseEntered(mouseEvent -> btnElectrical.setStyle(btnHover));
        btnElectrical.setOnMouseExited(mouseEvent -> btnElectrical.setStyle(btnNormal));

        btnLandscaping.setStyle(btnNormal);
        btnLandscaping.setOnMouseEntered(mouseEvent -> btnLandscaping.setStyle(btnHover));
        btnLandscaping.setOnMouseExited(mouseEvent -> btnLandscaping.setStyle(btnNormal));

        btnPlumbing.setStyle(btnNormal);
        btnPlumbing.setOnMouseEntered(mouseEvent -> btnPlumbing.setStyle(btnHover));
        btnPlumbing.setOnMouseExited(mouseEvent -> btnPlumbing.setStyle(btnNormal));

        btnHistory.setStyle(btnNormal);
        btnHistory.setOnMouseEntered(mouseEvent -> btnHistory.setStyle(btnHover));
        btnHistory.setOnMouseExited(mouseEvent -> btnHistory.setStyle(btnNormal));

        btnEdit.setStyle(btnNormal);
        btnEdit.setOnMouseEntered(mouseEvent -> btnEdit.setStyle(btnHover));
        btnEdit.setOnMouseExited(mouseEvent -> btnEdit.setStyle(btnNormal));

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "login-view.fxml", 1100, 750);
    }

    @FXML
    private void openHousekeeping(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "housekeeping-view.fxml", 1100, 750);
    }

    @FXML
    private void openElectrical(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "electrical-view.fxml", 1100, 750);
    }

    @FXML
    private void landscaping(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "landscaping-view.fxml", 1100, 750);
    }

    @FXML
    private void openPlumbing(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "plumbing-view.fxml", 1100, 750);
    }

    @FXML
    private void openHistory(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "customer-view-history.fxml", 1100, 750);
    }

    @FXML
    private void openEditProfile(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "customer-edit-profile.fxml", 1100, 750);
    }
}
