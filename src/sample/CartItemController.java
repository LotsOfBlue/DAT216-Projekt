package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import javax.swing.event.ChangeListener;
import java.net.URL;
import java.util.ResourceBundle;

public class CartItemController implements Initializable
{
    @FXML
    public Label nameLabel;
    @FXML
    public Label priceLabel;
    @FXML
    public Pane main_pane;
    @FXML
    public Spinner spinner;
    @FXML
    public Button deleteButton;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1));
    }
}
