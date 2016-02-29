package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    Button meatButton;
    @FXML
    Button fishButton;
    @FXML
    Button drinkButton;
    @FXML
    Button sweetsButton;
    @FXML
    Button fruitButton;
    @FXML
    Button breadButton;
    @FXML
    Button carbsButton;
    @FXML
    Button spiceButton;
    @FXML
    public VBox ItemArea;
    @FXML
    public Text currentCategory;
    public AnchorPane cartPane;
    public static AnchorPane cartItemPane;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        meatButton.  setOnAction(e -> Utils.setToValuesOfCategory(ItemArea, Utils.Category.meats, "Kött"));
        fishButton.  setOnAction(e -> Utils.setToValuesOfCategory(ItemArea, Utils.Category.fishes, "Fisk"));
        drinkButton. setOnAction(e -> Utils.setToValuesOfCategory(ItemArea, Utils.Category.drinks, "Dryck"));
        sweetsButton.setOnAction(e -> Utils.setToValuesOfCategory(ItemArea, Utils.Category.sweets, "Sötsaker"));
        fruitButton. setOnAction(e -> Utils.setToValuesOfCategory(ItemArea, Utils.Category.fruits, "Frukt & grönt"));
        breadButton. setOnAction(e -> Utils.setToValuesOfCategory(ItemArea, Utils.Category.breads, "Bröd"));
        spiceButton. setOnAction(e -> Utils.setToValuesOfCategory(ItemArea, Utils.Category.herbs, "Kryddor & örter"));
        carbsButton. setOnAction(e -> Utils.setToValuesOfCategory(ItemArea, Utils.Category.carbs, "Kolhydrater"));
        cartItemPane = cartPane;
    }
}
