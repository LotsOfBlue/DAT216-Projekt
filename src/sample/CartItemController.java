package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;

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

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        Spinner realSpinner = new Spinner(1,100,1);
        realSpinner.setLayoutX(spinner.getLayoutX());
        realSpinner.setLayoutY(spinner.getLayoutY());
        realSpinner.setPrefWidth(spinner.getPrefWidth());
        realSpinner.setPrefHeight(spinner.getPrefHeight());

        main_pane.getChildren().remove(spinner);
        main_pane.getChildren().add(realSpinner);
    }
}
