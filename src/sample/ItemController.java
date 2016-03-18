package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable
{
    @FXML
    public Label nameLabel;
    @FXML
    public Label singlePrice;
    @FXML
    public Button addButton;
    @FXML
    public Pane main_pane;
    @FXML
    public ImageView image;
    @FXML
    public Spinner spinner;
    @FXML
    public Label totalPrice;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1));
    }
}
