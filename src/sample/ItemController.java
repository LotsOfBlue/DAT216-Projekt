package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
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
    public Text description;
    @FXML
    public Label totalPrice;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        //Temporarily store the location of the dummy spinner
        Spinner oldSpinner = spinner;
        //Properly initialize the spinner
        spinner = new Spinner(1,100,1);
        spinner.setPrefWidth(oldSpinner.getPrefWidth());
        spinner.setPrefHeight(oldSpinner.getPrefHeight());
        spinner.setLayoutX(oldSpinner.getLayoutX() + 100);
        spinner.setLayoutY(oldSpinner.getLayoutY());

        main_pane.getChildren().remove(oldSpinner);
        main_pane.getChildren().add(spinner);
    }
}
