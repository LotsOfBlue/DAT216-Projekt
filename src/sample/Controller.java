package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    Button meatButton;
    @FXML
    Button fishButton;
    @FXML
    Button drinkButton;
    @FXML
    Button sweetsButton;
    @FXML
    Button fruitButton;
    @FXML
    Button breadButton;
    @FXML
    Button spiceButton;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

    }
}
