package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    public static HBox root;
    public static Pane storeFront;
    public static CartController cartController;
    private static final String toCheckout = "Gå till kassan";
    private static final String toStore = "Gå tillbaka";

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("storefront.fxml"));
        root = new HBox();

        primaryStage.setTitle("iMat");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.show();

        //Storefront
        FXMLLoader fxmlLoader = new FXMLLoader();
        storeFront = fxmlLoader.load(getClass().getResource("storefront_NoCart.fxml").openStream());
        Controller controller= (Controller) fxmlLoader.getController();
        storeFront.setPrefHeight(root.getPrefHeight());

        //Shopping cart
        FXMLLoader fxmlLoader_2 = new FXMLLoader();
        Pane cart = fxmlLoader_2.load(getClass().getResource("Cart.fxml").openStream());
        cartController = (CartController) fxmlLoader_2.getController();
        cart.setPrefHeight(root.getHeight());
        cart.setLayoutX(0);
        cartController.move_button.setText(toCheckout);
        storeFront.setPrefWidth(root.getWidth()-cart.getPrefWidth());
        cart.setMinWidth(cart.getPrefWidth());

        //Checkout
        FXMLLoader fxmlLoader_3 = new FXMLLoader();
        Pane checkOut = fxmlLoader_3.load(getClass().getResource("Checkout_NoCart.fxml").openStream());
        CheckOutController  checkOutController = (CheckOutController) fxmlLoader_3.getController();
        checkOut.setLayoutX(0);

        //Arrange all the panes
        Node[] children = {storeFront,cart,checkOut};
        root.getChildren().addAll(children);
        checkOut.setPrefHeight(root.getHeight());
    }

    private static boolean inShop = true;

    public static void toggleView()
    {
        if(inShop)
        {
            gotoCheckout();
            cartController.move_button.setText(toStore);
        }
        else
        {
            gotoStore();
            cartController.move_button.setText(toCheckout);
        }
    }

    public static void gotoCheckout()
    {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if(-root.getLayoutX()>storeFront.getPrefWidth()){
                    this.cancel();
                    inShop = false;
                }
                else
                    root.setLayoutX(root.getLayoutX() - storeFront.getPrefWidth() / (float) 500);
            }
        }, 0, 1);
    }

    public static void gotoStore()
    {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if(root.getLayoutX()>=0){
                    this.cancel();
                    inShop = true;
                }
                else
                    root.setLayoutX(root.getLayoutX() + storeFront.getPrefWidth() / (float) 500);
            }
        }, 0, 1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
