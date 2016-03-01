package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckOutController implements Initializable
{

    @FXML
    public Pane itemPane;
    @FXML
    public Button move_button;
    @FXML
    public Button save_Button;
    @FXML
    public Label amount_label;
    @FXML
    public Label sum_label;

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
    public Button payButton;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {

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
