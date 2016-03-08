package sample;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.*;

public class CartController implements Initializable
{

    @FXML
    public Pane itemPane;
    @FXML
    public Button move_button;
    @FXML
    public Button save_Button;
    @FXML
    public Label amount_label;
    @FXML
    public Label sum_label;

    public static Map<Product, Integer> cartProducts = new LinkedHashMap<>();

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        move_button.setOnAction(e->Main.toggleView());
        save_Button.setOnAction(e -> Main.addPopUp(new nameListView()));
    }
    void addProduct(Product p, int amount)
    {
        if(cartProducts.containsKey(p))
        {
            cartProducts.put(p,cartProducts.get(p) + amount);
        }
        else
        {
            cartProducts.put(p,amount);
        }
        updateCartView();
    }
    void removeProduct(Product p)
    {
        cartProducts.remove(p);
        updateCartView();
    }
    public void updateCartView()
    {
        itemPane.getChildren().clear();
        double price_ack = 0;
        int amount_ack = 0;

        for(Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            CartItemView p = new CartItemView(key, value);
            itemPane.getChildren().add(p);
            itemPane.getChildren().add(new Separator());
            price_ack += p.value;
            amount_ack += value;
        }
        amount_label.setText(Integer.toString(amount_ack));
        sum_label.setText(Double.toString(price_ack));
    }


    public void clearCart() {
        cartProducts.clear();
        updateCartView();
    }
}

