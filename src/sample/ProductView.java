package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.Product;
import sun.misc.JavaIOAccess;

import java.io.IOException;

/**
 * @author Daniel.
 */
public class ProductView extends HBox
{
    private Product product;
    private Label name;
    private Image image;
    private Label price;

    public ProductView (String name, Product product ,Image im, double price)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane pane = fxmlLoader.load(getClass().getResource("ProductItem.fxml").openStream());
            ItemController controller= (ItemController) fxmlLoader.getController();
            controller.nameLabel.setText(name);
            controller.priceLabel.setText(Double.toString(price));
            getChildren().add(pane);
        }
        catch (IOException ex)
        {
            System.out.println("couldn't load it yoo.");
        }
    }

    public Product getProduct(){
        return product;
    }

    final static int HEIGHT = 100;
    public static int getImageHeight(){
        return HEIGHT;
    }

    final static int WIDTH = 100;
    public static int getImageWidth(){
        return WIDTH;
    }


}
