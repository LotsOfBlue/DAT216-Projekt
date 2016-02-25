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

public class ItemController implements Initializable
{
    @FXML
    public Label nameLabel;
    @FXML
    public RadioButton spinner_placeHolder;
    @FXML
    public Label priceLabel;
    @FXML
    public Button Add_button;
    @FXML
    public Pane main_pane;
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        Spinner spinner = new Spinner(0,100,0);
        spinner.setLayoutX(spinner_placeHolder.getLayoutX());
        spinner.setLayoutY(spinner_placeHolder.getLayoutY());
        spinner.setPrefWidth(spinner_placeHolder.getPrefWidth());
        spinner.setPrefHeight(spinner_placeHolder.getPrefHeight());

        main_pane.getChildren().remove(spinner_placeHolder);
        main_pane.getChildren().add(spinner);

    }
}
