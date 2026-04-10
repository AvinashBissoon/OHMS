/** This class provides allows administrators to print customer list reports.
 *
 * @author Avinash Bissoon
 * @version 1.0
 *
 */


package com.cts.homeservices;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PrintReportController implements Initializable {
    static {
        Logger.getLogger(PrintReportController.class.getName());
    }


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
    private Button btnReturnToHome;
    @FXML
    private Button btnPrintReport;

    @FXML
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Set up of the styles to be used for Button elements
        String btnNormal = "-fx-font: 20px\"Leelawadee\";-fx-background-color:  white; -fx-text-fill: #036248; -fx-background-radius: 15; -fx-font-weight:bold;";
        String btnHover = "-fx-font: 20px\"Leelawadee\"; -fx-border-radius: 15; -fx-border-color:  #01402e; -fx-border-width:2; -fx-background-color: #036248; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight:bold;";

        //Apply styles to buttons
        btnReturnToHome.setStyle(btnNormal);
        btnReturnToHome.setOnMouseEntered(mouseEvent -> btnReturnToHome.setStyle(btnHover));
        btnReturnToHome.setOnMouseExited(mouseEvent -> btnReturnToHome.setStyle(btnNormal));

        btnPrintReport.setStyle(btnNormal);
        btnPrintReport.setOnMouseEntered(mouseEvent -> btnPrintReport.setStyle(btnHover));
        btnPrintReport.setOnMouseExited(mouseEvent -> btnPrintReport.setStyle(btnNormal));

        //Saves PDF on click of btnPrintReport Button
        btnPrintReport.setOnAction(event -> handlePrintReport());

        //Map customer columns to View Accounts Table
        colCustFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colCustLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCustMobileNum.setCellValueFactory(new PropertyValueFactory<>("mobilePhone"));
        colCustAddress1.setCellValueFactory(new PropertyValueFactory<>("streetAddress1"));
        colCustAddress2.setCellValueFactory(new PropertyValueFactory<>("streetAddress2"));
        colCustCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCustCountry.setCellValueFactory(new PropertyValueFactory<>("country"));


        loadCustomerData();
    }

    //Loads Customer data into the respective columns
    public void loadCustomerData(){
        customerList.clear();
        DatabaseConnection dc = new DatabaseConnection();
        String query = "SELECT * FROM tblCustomer";
        try {
            dc.ps = dc.con.prepareStatement(query);
            dc.rst = dc.ps.executeQuery();
            while (dc.rst.next()) {
                customerList.add(new Customer(
                        dc.rst.getInt("CustomerID"),
                        dc.rst.getString("FirstName"),
                        dc.rst.getString("LastName"),
                        dc.rst.getString("Email"),
                        dc.rst.getString("MobilePhone"),
                        dc.rst.getString("StreetAddress1"),
                        dc.rst.getString("StreetAddress2"),
                        dc.rst.getString("City"),
                        dc.rst.getString("Country")
                ));
            }
            tblViewCustomer.setItems(customerList);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (dc.rst != null) dc.rst.close();
                if (dc.ps != null) dc.ps.close();
                if (dc.con != null) dc.con.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
    }

    //Sets up all PDF and table Parameters
    @FXML
    private void handlePrintReport() {
        String fileName = "CustomerListReport.pdf";
        Document document = new Document(PageSize.A4.rotate());

        try{
            File file = new File(fileName);
            PdfWriter.getInstance(document, new FileOutputStream(file.getAbsolutePath()));
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            document.add(new Paragraph("Customer List Report", font));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);

            table.addCell("First Name");
            table.addCell("Last Name");
            table.addCell("Email");
            table.addCell("Mobile Number");
            table.addCell("Address 1");
            table.addCell("Address 2");
            table.addCell("City");
            table.addCell("Country");

            for (Customer c : customerList) {
                table.addCell(c.getFirstName());
                table.addCell(c.getLastName());
                table.addCell(c.getEmail());
                table.addCell(c.getMobilePhone());
                table.addCell(c.getStreetAddress1());
                table.addCell(c.getStreetAddress2());
                table.addCell(c.getCity());
                table.addCell(c.getCountry());
            }

            document.add(table);
            document.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Online Home Service Solution: Info Dialog");
            alert.setHeaderText("Report Information");
            alert.setContentText("Report successfully saved to " + file.getAbsolutePath());
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Takes the administrator back to the Administrator Dashboard
    @FXML
    private void backToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OnlineHomeServiceApp.changeScene(stage, "admin-dashboard.fxml", 1100, 750);
    }
}


