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

    public ProductView (Product product,double size)
    {
        this.product = product;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane pane = fxmlLoader.load(getClass().getResource("ProductItem.fxml").openStream());
            ItemController controller = (ItemController) fxmlLoader.getController();
            controller.nameLabel.setText(product.getName());
            controller.singlePrice.setText(Double.toString(product.getPrice()) + " " + product.getUnit());
            controller.image.setImage(new Image("resources - projekt (fx)/images/" + product.getImageName()));
            controller.addButton.setOnAction(e->Main.cartController.addProduct(product, (Integer)controller.spinner.getValue()));
            getChildren().add(pane);
            //pane.setPrefWidth(size);
        }
        catch (IOException ex)
        {
            System.out.println("couldn't load it yoo.");
        }
    }

    public Product getProduct(){
        return product;
    }

    final static int HEIGHT = 110;
    public static int getImageHeight(){
        return HEIGHT;
    }


    final static int WIDTH = 110;
    public static int getImageWidth(){
        return WIDTH;
    }
}
