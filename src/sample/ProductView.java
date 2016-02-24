package sample;

import javafx.scene.Node;
import javafx.scene.image.Image;
import se.chalmers.ait.dat215.project.Product;

/**
 * @author Daniel.
 */
public abstract class ProductView extends Node
{
    public abstract Product getProduct();
    public abstract ProductView createView(Product p);
}
