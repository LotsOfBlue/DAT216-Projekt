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
    public abstract int getImageHeight();
    public abstract int getImageWidth();
    public abstract ProductView createView(String name, Product p,Image im,double price);
}
