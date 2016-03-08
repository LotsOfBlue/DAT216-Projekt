package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.Product;

import java.awt.geom.Area;
import java.net.URL;
import java.util.Map;
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
    public TextField searchField;
    public Button searchButton;
    public Label currentCategory;
    public ScrollPane AreaParent;

    @FXML
    public Button list_button;
    @FXML
    public VBox earlierPerchasesVBox;
    @FXML
    public Tab earlierPurachases;
    @FXML
    public VBox SavedListsVBox;
    @FXML
    public Tab savedLists;
    @FXML
    public TabPane listsView;
    @FXML
    public AnchorPane shoppingListView;
    @FXML
    public AnchorPane normalview;

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
        searchButton.setOnAction(e -> Utils.setToValuesFromSearch(ItemArea, searchField.getText().trim()));
        toggleListsView();
    }
    public void search()
    {
        Utils.setToValuesFromSearch(ItemArea, searchField.getText().trim());
    }

    public void toggleListsView()
    {
        shoppingListView.setVisible(!shoppingListView.isVisible());
        SavedListsVBox.getChildren().clear();
        updateLists();
    }
    public void setVisibleListView(boolean visible)
    {
        shoppingListView.setVisible(visible);
    }

    public void updateLists()
    {
        SavedListsVBox.getChildren().clear();
        for(savedList list : Utils.allSavedLists)
        {
            Map<Product,Integer> map = list.map;
            SavedListsVBox.getChildren().add(new sample.ListView(list.name,map));
            SavedListsVBox.getChildren().add(new Separator());
        }

        earlierPerchasesVBox.getChildren().clear();
        for( savedList list: Utils.allHistoryLists)
        {
            Map<Product,Integer> map = list.map;
            earlierPerchasesVBox.getChildren().add(new sample.ListView(list.name,map));
            earlierPerchasesVBox.getChildren().add(new Separator());
        }
    }
}
