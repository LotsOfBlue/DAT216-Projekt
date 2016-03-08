package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;
import java.util.Map;

/**
 * @author Daniel.
 */
public class ListView extends Pane
{
    Map <Product,Integer> backing_map;
    public  ListView(String name,Map <Product,Integer> backing_map)
    {
        this.backing_map = backing_map;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane pane = fxmlLoader.load(getClass().getResource("List.fxml").openStream());
            ListController controller = (ListController) fxmlLoader.getController();
            controller.ListName.setText(name);
            controller.addButton.setOnAction(e->addListToCart());
            setMinHeight(pane.getMinHeight());
            getChildren().add(pane);
        }
        catch (IOException ex)
        {
            System.out.println("couldn't load it yoo.");
        }

    }
    public void addListToCart()
    {
        for(Map.Entry<Product, Integer> entry : backing_map.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            Main.cartController.addProduct(key,value);
        }
    }
}
