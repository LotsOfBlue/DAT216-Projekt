package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML
    public Button deleteButton;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        deleteButton.setOnAction(e -> Utils.removeItemFromCart(deleteButton.getParent().getParent()));

        //Temporarily store the location of the dummy spinner
        Spinner oldSpinner = spinner;
        //Properly initialize the spinner
        spinner = new Spinner(1,100,1);
        spinner.setLayoutX(oldSpinner.getLayoutX());
        spinner.setLayoutY(oldSpinner.getLayoutY());
        spinner.setPrefWidth(oldSpinner.getPrefWidth());
        spinner.setPrefHeight(oldSpinner.getPrefHeight());

        main_pane.getChildren().remove(oldSpinner);
        main_pane.getChildren().add(spinner);
    }
}
