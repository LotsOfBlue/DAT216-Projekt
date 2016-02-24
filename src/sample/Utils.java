package sample;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;
import se.chalmers.ait.dat215.project.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel.
 */
public class Utils
{
    static IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    ProductCategory[] categories = ProductCategory.values();

    public static void addItemToCart(Pane shoppingPane, Product product, ShoppingView view)
    {
        dataHandler.getShoppingCart().addProduct(product);
    }


    public static void setToValuesOfCategory(Pane pane, Category category, ProductView view)
    {
        pane.getChildren().clear();
        List<Product> products = getProductsFromCategory(category);
        for(Product p: products)
        {
            Node n = view.createView(p.getName(),
                    dataHandler.getFXImage(p,view.getImageWidth(),view.getImageHeight(),true),
                    p.getPrice(),p.getProductId());
            pane.getChildren().add(n);
        }
    }

    enum Category
    {
        drycker,
        kolhydrated,
        frukt,
        kött,
        bröd,
        fisk,
        kryddor,
        sötsaker,
    }

    public static List<Product> getProductsFromCategory(Category cat)
    {
        ArrayList<Product> productCategories = new ArrayList<>();
        List<ProductCategory> categories = getCats(cat);
        for(ProductCategory c: categories)
        {
            productCategories.addAll(dataHandler.getProducts(c));
        }
        return productCategories;
    }

    private static List<ProductCategory> getCats(Category cat)
    {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        //not all of theirs are mapped to ours fix later.
        switch (cat)
        {
            case drycker:
                productCategories.add(ProductCategory.COLD_DRINKS);
                productCategories.add(ProductCategory.HOT_DRINKS);
                break;
            case kolhydrated:
                productCategories.add(ProductCategory.PASTA);
                productCategories.add(ProductCategory.POTATO_RICE);
                break;
            case frukt:
                productCategories.add(ProductCategory.FRUIT);
                break;
            case kött:
                productCategories.add(ProductCategory.MEAT);
                break;
            case bröd:
                productCategories.add(ProductCategory.BREAD);
                break;
            case fisk:
                productCategories.add(ProductCategory.FISH);
                break;
            case kryddor:
                productCategories.add(ProductCategory.HERB);
                break;
            case sötsaker:
                productCategories.add(ProductCategory.SWEET);
                break;
        }
        return productCategories;
    }



}
