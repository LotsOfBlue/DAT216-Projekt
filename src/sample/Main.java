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
    public static Controller storeController;
    public static CartController cartController;
    private static final String toCheckout = "Gå till kassan";
    private static final String toStore = "Gå tillbaka";
    private static Stage stage;
    private static Scene mainScene;
    private static Scene purchaseScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("storefront.fxml"));
        root = new HBox();

        stage = primaryStage;
        mainScene = new Scene(root);

        primaryStage.setTitle("iMat");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.show();

        //Storefront
        FXMLLoader fxmlLoader = new FXMLLoader();
        storeFront = fxmlLoader.load(getClass().getResource("storefront_NoCart.fxml").openStream());
        storeController = (Controller) fxmlLoader.getController();

        //Shopping cart
        FXMLLoader fxmlLoader_2 = new FXMLLoader();
        Pane cart = fxmlLoader_2.load(getClass().getResource("Cart.fxml").openStream());
        cart.setOnMousePressed(e->storeController.setVisibleListView(false));
        cartController = (CartController) fxmlLoader_2.getController();
        cart.setPrefHeight(root.getHeight());
        cart.setLayoutX(0);
        cartController.move_button.setText(toCheckout);
        double width = root.getWidth()-cart.getPrefWidth();

        storeFront.setPrefWidth(width);
        storeController.normalview.setPrefWidth(width);
        storeController.shoppingListView.setPrefWidth(width);
        cart.setMinWidth(cart.getPrefWidth());

        //Checkout
        FXMLLoader fxmlLoader_3 = new FXMLLoader();
        Pane checkOut = fxmlLoader_3.load(getClass().getResource("Checkout_NoCart.fxml").openStream());
        CheckOutController  checkOutController = (CheckOutController) fxmlLoader_3.getController();
        checkOut.setLayoutX(0);
        checkOut.setPrefHeight(root.getHeight());
        checkOut.setMinWidth(width);

        checkOut.setPrefWidth(width);
        checkOutController.left_split.setPrefWidth(width/2);

        //Purchase complete
        FXMLLoader loader = new FXMLLoader();
        Pane purchaseCompletePane = loader.load(getClass().getResource("PurchaseComplete.fxml").openStream());
        PurchaseCompleteController purchaseController = loader.getController();

        //Arrange all the panes
        Node[] children = {storeFront,cart,checkOut,purchaseCompletePane};
        root.getChildren().addAll(children);
        Utils.loadListsFromFile();
    }

    private static boolean inShop = true;

    public static void toggleView()
    {
        storeController.setVisibleListView(false);
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

    private static double calculateSpeed(double progress)
    {
        //simple sin seams to look the best me (only tested some simple functions)
        return Math.sin(progress*Math.PI);
        /*
        // second grade
        progress = (progress-0.5)*2;
        return 1-(progress * progress);
        // linear
        //return (1-Math.abs(progress-0.5)*2);
        //return 0.5;
         */
    }

    static boolean isMoving = false;
    public static void gotoCheckout()
    {
        if(isMoving)return;
        Timer t = new Timer();
        isMoving = true;
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                double progress = -root.getLayoutX()/storeFront.getPrefWidth();
                if(-root.getLayoutX()>storeFront.getPrefWidth()){
                    this.cancel();
                    root.setLayoutX(-storeFront.getPrefWidth());
                    inShop = false;
                    isMoving = false;
                }
                else
                {
                    double speed = calculateSpeed(progress);
                    root.setLayoutX(root.getLayoutX() + (speed+0.05) * (-storeFront.getPrefWidth()/ (float) 300));
                }
            }
        }, 0, 1);
    }

    public static void gotoStore()
    {
        if(isMoving)return;
        Timer t = new Timer();
        isMoving = true;
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                double progress = 1-root.getLayoutX()/-storeFront.getPrefWidth();
                if(root.getLayoutX()>=0){
                    this.cancel();
                    root.setLayoutX(0);
                    inShop = true;
                    isMoving = false;
                }
                else
                {
                    double speed = calculateSpeed(progress);
                    root.setLayoutX(root.getLayoutX() + (speed+0.1) * (storeFront.getPrefWidth()/ (float) 300));
                }
            }
        }, 0, 1);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void purchaseComplete() {
        stage.setScene(purchaseScene);
    }

    public static void resetStore() {
        cartController.clearCart();
        stage.setScene(mainScene);
        gotoStore();
    }
}
