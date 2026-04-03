package com.cts.homeservices;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AdminViewAccountsController implements Initializable {
    static {
        Logger.getLogger(AdminViewAccountsController.class.getName());
    }

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabCustomer;

    @FXML
    private Tab tabEmployee;

    @FXML
    private TableView<Customer> tblViewCustomer;

    @FXML
    private TableColumn<Customer, String> colCustFirstName;
    @FXML
    private TableColumn<Customer, String> colCustLastName;
    @FXML
    private TableColumn<Customer, String> colCustEmail;
    @FXML
    private TableColumn<Customer, String> colCustMobileNum;
    @FXML
    private TableColumn<Customer, String> colCustAddress1;
    @FXML
    private TableColumn<Customer, String> colCustAddress2;
    @FXML
    private TableColumn<Customer, String> colCustCity;
    @FXML
    private TableColumn<Customer, String> colCustCountry;

    @FXML
    private TableView<Employee> tblViewEmployee;
    @FXML
    private TableColumn<Customer, String> colEmpFirstName;
    @FXML
    private TableColumn<Customer, String> colEmpLastName;
    @FXML
    private TableColumn<Customer, String> colEmpEmail;
    @FXML
    private TableColumn<Customer, String> colEmpMobileNum;
    @FXML
    private TableColumn<Customer, String> colEmpAddress1;
    @FXML
    private TableColumn<Customer, String> colEmpAddress2;
    @FXML
    private TableColumn<Customer, String> colEmpCity;
    @FXML
    private TableColumn<Customer, String> colEmpCountry;

    @FXML
    private TextField tbFirstName;

    @FXML
    private TextField tbLastName;

    @FXML
    private TextField tbEmail;

    @FXML
    private TextField tbMobileNumber;

    @FXML
    private TextField tbAddressLine1;

    @FXML
    private TextField tbAddressLine2;

    @FXML
    private TextField tbCity;

    @FXML
    private TextField tbCountry;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReturnToHome;

    @FXML
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting up the styles to be used
        String btnNormal = "-fx-font: 20px\"Leelawadee\";-fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 20px\"Leelawadee\"; -fx-border-radius: 15; -fx-border-color:  #01402e; -fx-border-width:2; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";


        btnReturnToHome.setStyle(btnNormal);
        btnReturnToHome.setOnMouseEntered(mouseEvent -> btnReturnToHome.setStyle(btnHover));
        btnReturnToHome.setOnMouseExited(mouseEvent -> btnReturnToHome.setStyle(btnNormal));



        //Map customer columns
        colCustFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colCustLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCustMobileNum.setCellValueFactory(new PropertyValueFactory<>("mobilePhone"));
        colCustAddress1.setCellValueFactory(new PropertyValueFactory<>("streetAddress1"));
        colCustAddress2.setCellValueFactory(new PropertyValueFactory<>("streetAddress2"));
        colCustCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCustCountry.setCellValueFactory(new PropertyValueFactory<>("country"));


        //map employee columns
        colEmpFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colEmpLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmpMobileNum.setCellValueFactory(new PropertyValueFactory<>("mobilePhone"));
        colEmpAddress1.setCellValueFactory(new PropertyValueFactory<>("streetAddress1"));
        colEmpAddress2.setCellValueFactory(new PropertyValueFactory<>("streetAddress2"));
        colEmpCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colEmpCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

        tblViewCustomer.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if (newSelection != null) {
                tbFirstName.setText(String.valueOf(newSelection.getFirstName()));
                tbLastName.setText(newSelection.getLastName());
                tbEmail.setText(newSelection.getEmail());
                tbMobileNumber.setText(String.valueOf(newSelection.getMobilePhone()));
                tbAddressLine1.setText(newSelection.getStreetAddress1());
                tbAddressLine2.setText(newSelection.getStreetAddress2());
                tbCity.setText(newSelection.getCity());
                tbCountry.setText(newSelection.getCountry());
            }
        });

        tblViewEmployee.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if (newSelection != null) {
                tbFirstName.setText(String.valueOf(newSelection.getFirstName()));
                tbLastName.setText(newSelection.getLastName());
                tbEmail.setText(newSelection.getEmail());
                tbMobileNumber.setText(String.valueOf(newSelection.getMobilePhone()));
                tbAddressLine1.setText(newSelection.getStreetAddress1());
                tbAddressLine2.setText(newSelection.getStreetAddress2());
                tbCity.setText(newSelection.getCity());
                tbCountry.setText(newSelection.getCountry());
            }
        });

        loadCustomerData();
        loadEmployeeData();
    }

    public void loadCustomerData(){
        customerList.clear();
        DatabaseConnection dc = new DatabaseConnection();

        String query = "SELECT * FROM tblcustomer";

        try {
            dc.ps = dc.con.prepareStatement(query);
            dc.rst = dc.ps.executeQuery();

            while (dc.rst.next()) {
                customerList.add(new Customer(
                        dc.rst.getString("firstname"),
                        dc.rst.getString("lastname"),
                        dc.rst.getString("email"),
                        dc.rst.getString("mobilephone"),
                        dc.rst.getString("streetaddress1"),
                        dc.rst.getString("streetaddress2"),
                        dc.rst.getString("city"),
                        dc.rst.getString("country")
                ));
            }
            tblViewCustomer.setItems(customerList);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadEmployeeData(){
       employeeList.clear();
        DatabaseConnection dc = new DatabaseConnection();

        String query = "SELECT * FROM tblemployee";

        try {
            dc.ps = dc.con.prepareStatement(query);
            dc.rst = dc.ps.executeQuery();

            while (dc.rst.next()) {
                employeeList.add(new Employee(
                        dc.rst.getString("firstname"),
                        dc.rst.getString("lastname"),
                        dc.rst.getString("email"),
                        dc.rst.getString("mobilephone"),
                        dc.rst.getString("streetaddress1"),
                        dc.rst.getString("streetaddress2"),
                        dc.rst.getString("city"),
                        dc.rst.getString("country")
                ));
            }
            tblViewEmployee.setItems(employeeList);
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "admin-dashboard.fxml", 1100, 750);
    }

}