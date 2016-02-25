package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable
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

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        move_button.setOnAction(e->Main.toggleView());
    }
}
