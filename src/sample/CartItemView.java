package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * @author Daniel.
     */
public class CartItemView extends HBox
{
    public final Product product;
    public double value;
    public int amount;

    public CartItemView(Product product, int amount)
    {
        this.product = product;
        this.amount = amount;
        this.value = amount * product.getPrice();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane pane = fxmlLoader.load(getClass().getResource("CartItem.fxml").openStream());
            CartItemController controller = (CartItemController) fxmlLoader.getController();
            controller.nameLabel.setText(product.getName());
            controller.priceLabel.setText(Double.toString(value) + " kr");
            controller.spinner.increment(amount - 1);
            controller.deleteButton.setOnAction(e -> Main.cartController.removeProduct(product));
            controller.spinner.valueProperty().addListener((obs, oldValue, newValue) ->
            {
                Main.cartController.addProduct(product,(Integer)newValue-(Integer)oldValue);
            });
            getChildren().add(pane);
        }
        catch (IOException ex)
        {
            System.out.println("couldn't load it yoo.");
        }
    }


}
