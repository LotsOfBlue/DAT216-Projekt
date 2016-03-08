package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable
{
    @FXML
    public Label ListName;
    @FXML
    public Button addButton;
    @FXML
    public Pane main_pane;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {

    }

}
