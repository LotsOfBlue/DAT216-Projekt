package sample;

import javafx.scene.Parent;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Daniel.
 */
public class Utils
{
    static IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    static ProductCategory[] categories = ProductCategory.values();

    public static List<Map<Product,Integer>> allSavedLists = new ArrayList<>();
    public static List<Map<Product,Integer>> allHistoryLists = new ArrayList<>();

    public static void removeItemFromCart(Parent item) {
        Main.cartController.itemPane.getChildren().remove(item);
        CartItemView cartItem = (CartItemView) item;
        Main.cartController.amount_label.setText("" + (Integer.parseInt(Main.cartController.amount_label.getText()) - cartItem.amount));
        Main.cartController.sum_label.setText("" + (Double.parseDouble(Main.cartController.sum_label.getText()) - cartItem.value));
    }

    public static void setToValuesOfCategory(Pane pane, Category category, String str)
    {
        pane.getChildren().clear();
        VBox box = new VBox();
        List<Product> products = getProductsFromCategory(category);
        for(Product p: products)
        {
            ProductView view = new ProductView(p,500);
            box.getChildren().add(view);
            box.getChildren().add(new Separator());
        }
        pane.getChildren().add(box);
        Main.storeController.currentCategory.setText(str);
    }

    public static void setToValuesFromSearch(Pane pane, String search)
    {
        //Don't search if the input is just whitespace
        if (!search.equals("")) {
            pane.getChildren().clear();
            VBox box = new VBox();
            List<Product> products = dataHandler.findProducts(search);
            for(Product p: products)
			{
				ProductView view = new ProductView(p,pane.getPrefWidth());
				box.getChildren().add(view);
				box.getChildren().add(new Separator());
			}
            pane.getChildren().add(box);
            Main.storeController.currentCategory.setText("Sökresultat");
        }
        Main.storeController.searchField.clear();
    }

    public enum Category
    {
        drinks,
        carbs,
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
            case carbs:
                productCategories.add(ProductCategory.PASTA);
                productCategories.add(ProductCategory.POTATO_RICE);
                break;
            case fruits:
                productCategories.add(ProductCategory.FRUIT);
                productCategories.add(ProductCategory.CITRUS_FRUIT);
                productCategories.add(ProductCategory.EXOTIC_FRUIT);
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

    public static void newSavedList() {
        allSavedLists.add(new HashMap<Product, Integer>(CartController.cartProducts));
    }

    public static void newHistoryList() {
        allHistoryLists.add(new HashMap<Product, Integer>(CartController.cartProducts));
    }
}
