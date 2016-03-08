package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseCompleteController implements Initializable{

	@FXML
	public Button storeButton;
	@FXML
	public Button saveButton;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		saveButton.setOnAction(e -> Main.addPopUp(new nameListView()));
		storeButton.setOnAction(e -> Main.resetStore());
	}
}
