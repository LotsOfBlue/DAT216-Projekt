package sample;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.*;

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
            Node n = view.createView(p.getName(),p,
                    dataHandler.getFXImage(p,view.getImageWidth(),view.getImageHeight(),true),
                    p.getPrice());
            pane.getChildren().add(n);
        }
    }

    enum Category
    {
        drinks,
        carbons,
        fruits,
        meats,
        breads,
        fishes,
        herbs,
        sweets,
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
            case drinks:
                productCategories.add(ProductCategory.COLD_DRINKS);
                productCategories.add(ProductCategory.HOT_DRINKS);
                break;
            case carbons:
                productCategories.add(ProductCategory.PASTA);
                productCategories.add(ProductCategory.POTATO_RICE);
                break;
            case fruits:
                productCategories.add(ProductCategory.FRUIT);
                break;
            case meats:
                productCategories.add(ProductCategory.MEAT);
                break;
            case breads:
                productCategories.add(ProductCategory.BREAD);
                break;
            case fishes:
                productCategories.add(ProductCategory.FISH);
                break;
            case herbs:
                productCategories.add(ProductCategory.HERB);
                break;
            case sweets:
                productCategories.add(ProductCategory.SWEET);
                break;
        }
        return productCategories;
    }



}
