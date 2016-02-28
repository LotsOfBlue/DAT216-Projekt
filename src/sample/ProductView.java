package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;

/**
 * @author Daniel.
 */
public class ProductView extends HBox
{
    private Product product;

    public ProductView (String name, Product product ,Image im, double price)
    {
        this.product = product;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane pane = fxmlLoader.load(getClass().getResource("ProductItem.fxml").openStream());
            ItemController controller= (ItemController) fxmlLoader.getController();
            controller.nameLabel.setText(name);
            controller.singlePrice.setText(Double.toString(price));
            controller.addButton.setOnAction(e->Utils.addItemToCart(product));
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
