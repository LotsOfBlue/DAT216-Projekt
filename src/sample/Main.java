package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
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
    private static  Pane[] children_;

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

        primaryStage.setOnCloseRequest(e->
        {
            Platform.exit();
        });

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
        //checkOutController.left_split.setPrefWidth(width/2);

        //Purchase complete
        FXMLLoader loader = new FXMLLoader();
        Pane purchaseCompletePane = loader.load(getClass().getResource("PurchaseComplete.fxml").openStream());
        PurchaseCompleteController purchaseController = loader.getController();

        //Arrange all the panes
        Pane[] children = {storeFront,cart,checkOut,purchaseCompletePane};
        children_ = children;
        root.getChildren().addAll(children);
        Utils.loadListsFromFile();
    }

    public static void toggleView()
    {
        storeController.setVisibleListView(false);
        if(activeScene != 1)
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
        gotoScene(1);
    }

    public static void gotoStore()
    {
        gotoScene(0);
    }

    public static void gotoPurchaseComplete()
    {
        gotoScene(3);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void purchaseComplete() {
        gotoPurchaseComplete();
    }

    public static double targetLayoutX = 0;
    public static double initialLayoutX = 0;
    public static int activeScene = 0;

    private static void gotoScene(int sceneIndex)
    {
        if(isMoving)return;
        targetLayoutX = 0;
        for(int i = 0;i<sceneIndex;i++)
        {
            targetLayoutX-=children_[i].getPrefWidth();
        }
        initialLayoutX = root.getLayoutX();
        isMoving = true;
        activeScene = sceneIndex;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {

                double remaining = targetLayoutX-root.getLayoutX();
                double initial = (targetLayoutX-initialLayoutX);
                double progress = 1-(remaining/initial);
                if(Math.signum(remaining)!=Math.signum(initial)){
                    this.cancel();
                    root.setLayoutX(targetLayoutX);
                    isMoving = false;
                }
                else
                {
                    double speed = calculateSpeed(progress);
                    root.setLayoutX(root.getLayoutX() + (speed+0.1) * (initial / (float) 300));
                }
            }
        }, 30, 1);
    }

    public static void resetStore() {
        cartController.clearCart();
        stage.setScene(mainScene);
        gotoStore();
    }
}
