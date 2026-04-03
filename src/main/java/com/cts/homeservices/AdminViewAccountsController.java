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
    private TableColumn<Employee, String> colEmpFirstName;
    @FXML
    private TableColumn<Employee, String> colEmpLastName;
    @FXML
    private TableColumn<Employee, String> colEmpEmail;
    @FXML
    private TableColumn<Employee, String> colEmpMobileNum;
    @FXML
    private TableColumn<Employee, String> colEmpAddress1;
    @FXML
    private TableColumn<Employee, String> colEmpAddress2;
    @FXML
    private TableColumn<Employee, String> colEmpCity;
    @FXML
    private TableColumn<Employee, String> colEmpCountry;

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

    @FXML
    private int selectedUserId = -1;




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
                selectedUserId = newSelection.getCustomerId();
                fillFields(newSelection.getFirstName(), newSelection.getLastName(), newSelection.getEmail(), newSelection.getMobilePhone(), newSelection.getStreetAddress1(), newSelection.getStreetAddress2(), newSelection.getCity(), newSelection.getCountry());
            }
        });

        tblViewEmployee.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if (newSelection != null) {
                selectedUserId = newSelection.getEmployeeId();
                fillFields(newSelection.getFirstName(), newSelection.getLastName(), newSelection.getEmail(), newSelection.getMobilePhone(), newSelection.getStreetAddress1(), newSelection.getStreetAddress2(), newSelection.getCity(), newSelection.getCountry());
            }
        });

        loadCustomerData();
        loadEmployeeData();
    }
    private void fillFields(String fn, String ln, String em, String ph, String a1, String a2, String ct, String co) {
        tbFirstName.setText(fn); tbLastName.setText(ln); tbEmail.setText(em); tbMobileNumber.setText(ph);
        tbAddressLine1.setText(a1); tbAddressLine2.setText(a2); tbCity.setText(ct); tbCountry.setText(co);
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
                        dc.rst.getInt("customerid"),
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
                        dc.rst.getInt("employeeid"),
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
    private void saveEdit(ActionEvent event) {
        if (selectedUserId == -1) return;
        DatabaseConnection dc = new DatabaseConnection();

        String query;

        Tab activeTab = tabPane.getSelectionModel().getSelectedItem();


        if (activeTab == tabCustomer) {
            query =  "UPDATE tblcustomer SET firstname = ?, lastname = ?, email = ?, mobilephone = ?, streetaddress1 = ?, streetaddress2 = ?, city = ?, country = ? WHERE customerid = ?";
        } else  {
            query =  "UPDATE tblemployee SET firstname = ?, lastname = ?, email = ?, mobilephone = ?, streetaddress1 = ?, streetaddress2 = ?, city = ?, country = ? WHERE employeeid = ?";
        }

        try {
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, tbFirstName.getText());
            dc.ps.setString(2, tbLastName.getText());
            dc.ps.setString(3, tbEmail.getText());
            dc.ps.setString(4, tbMobileNumber.getText());
            dc.ps.setString(5, tbAddressLine1.getText());
            dc.ps.setString(6, tbAddressLine2.getText());
            dc.ps.setString(7, tbCity.getText());
            dc.ps.setString(8, tbCountry.getText());
            dc.ps.setInt(9, selectedUserId);


            if (dc.ps.executeUpdate() >0) {
                loadCustomerData();
                loadEmployeeData();
                clearFields();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Online Home Service Solution: Info Dialog");
                alert.setHeaderText("Account Information");
                alert.setContentText("Account Information Updated Successfully!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void clearFields() {
        tbFirstName.clear();
        tbLastName.clear();
        tbEmail.clear();
        tbMobileNumber.clear();
        tbAddressLine1.clear();
        tbAddressLine2.clear();
        tbCity.clear();
        tbCountry.clear();
        selectedUserId = -1;
    }


    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "admin-dashboard.fxml", 1100, 750);
    }

}