package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.rmi.CORBA.Util;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Daniel.
 */
public class ListPopup implements Initializable
{
    @FXML
    public Button cancel_button;
    @FXML
    public Button save_button;
    @FXML
    public TextField name;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cancel_button.setOnAction(e->
        {
            Main.removePopUp();
        });
        save_button.setOnAction(e-> {
            Utils.newSavedList(name.getText());
            Main.removePopUp();
        });
    }
}
