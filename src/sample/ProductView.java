package sample;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.Product;

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
        this.product = product;
        this.name = new Label(name);
        this.image = im;
        this.price = new Label(Double.toString(price));
        Node[] children = {this.name,this.price};
        getChildren().addAll(children);

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
