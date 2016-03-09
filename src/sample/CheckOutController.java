package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.rmi.CORBA.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

public class CheckOutController implements Initializable
{


    @FXML
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField addressField;
    public TextField locationField;
    public TextField postcodeField;
    public TextField phoneField;
    public TextField cardField;
    public TextField cvcField;

    @FXML
    public Label firstNameError;
    public Label lastNameError;
    public Label addressError;
    public Label postcodeError;
    public Label locationError;
    public Label phoneError;
    public Label cardError;
    public Label cvcError;

    @FXML
    public SplitPane split_pane;
    public AnchorPane left_split;
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        firstNameError.setText("");
        lastNameError.setText("");
        addressError.setText("");
        postcodeError.setText("");
        locationError.setText("");
        phoneError.setText("");
        cardError.setText("");
        cvcError.setText("");
        loadInfo();
    }

    public void performPayment(ActionEvent event) {
        //Clear error messages
        firstNameError.setText("");
        lastNameError.setText("");
        addressError.setText("");
        postcodeError.setText("");
        locationError.setText("");
        phoneError.setText("");
        cardError.setText("");
        cvcError.setText("");
        //Check if the fields contain valid info
        if(fieldsValid()) {
            Date d = new Date();
            Utils.newHistoryList(d.getDate() + "/" + d.getMonth()+"-"+(d.getYear()+1900));
            saveInfo();
            Main.purchaseComplete();
            Utils.backendPurchase();

        }
    }
    void saveInfo()
    {
        try
        {
            File f = new File("userInfo.txt");
            PrintWriter writer = new PrintWriter(f);
            writer.println(firstNameField.getText());
            writer.println(lastNameField.getText());
            writer.println(addressField.getText());
            writer.println(postcodeField.getText());
            writer.println(locationField.getText());
            writer.println(phoneField.getText());
            writer.close();
        }
        catch (Exception ex)
        {
            // do nothing
        }
    }
    final String userDataFilePath = "userInfo.txt";
    void loadInfo()
    {
        try
        {
            File f = new File("userInfo.txt");
            Scanner s = new Scanner(f);
            firstNameField.setText(s.nextLine());
            lastNameField.setText(s.nextLine());
            addressField.setText(s.nextLine());
            postcodeField.setText(s.nextLine());
            locationField.setText(s.nextLine());
            phoneField.setText(s.nextLine());

        }
        catch (Exception ex)
        {
            //do nothing
        }
    }

    private boolean fieldsValid() {
        boolean value = true;

        if(firstNameField.getText().trim().equals("")) {
            firstNameError.setText("Du måste ange ett förnamn.");
            value = false;
        }
        if(lastNameField.getText().trim().equals("")) {
            lastNameError.setText("Du måste ange ett efternamn.");
            value = false;
        }
        if(addressField.getText().trim().equals("")) {
            addressError.setText("Du måste ange en adress.");
            value = false;
        }
        if(postcodeField.getText().trim().equals("")) {
            postcodeError.setText("Du måste ange ett postnummer.");
            value = false;
        }
        if(locationField.getText().trim().equals("")) {
            locationError.setText("Du måste ange en ort.");
            value = false;
        }
        if(phoneField.getText().trim().equals("")) {
            phoneError.setText("Du måste ange ett telefonnummer.");
            value = false;
        }
        if(cardField.getText().trim().equals("")) {
            cardError.setText("Du måste ange ett kortnummer.");
            value = false;
        }
        if(cvcField.getText().trim().equals("")) {
            cvcError.setText("Du måste ange en säkerhetskod.");
            value = false;
        }

        return value;
    }
}
