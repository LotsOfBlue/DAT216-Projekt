package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;

/**
 * @author Daniel.
     */
public class CartItemView extends HBox
{
    public CartItemView(Product product)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane pane = fxmlLoader.load(getClass().getResource("CartItem.fxml").openStream());
            CartItemController controller = (CartItemController) fxmlLoader.getController();
            controller.nameLabel.setText(product.getName());
            controller.priceLabel.setText(product.getPrice() + " kr");
            getChildren().add(pane);
        }
        catch (IOException ex)
        {
            System.out.println("couldn't load it yoo.");
        }
    }


}
