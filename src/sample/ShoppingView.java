package sample;

import javafx.scene.Node;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * @author Daniel.
 */
public abstract class ShoppingView extends Node
{
    public abstract ShoppingView createView(ShoppingItem item);
}
