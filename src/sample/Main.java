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

    static Pane realRoot;
    static Node popup;
    static Pane panel;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("storefront.fxml"));


        root = new HBox();

        realRoot = new Pane();
        realRoot.getChildren().add(root);
        root.setOnMousePressed(e->{
            removePopUp();
        });

        root.setMaxWidth(realRoot.getMaxWidth());
        root.setMaxHeight(realRoot.getMaxHeight());

        stage = primaryStage;
        mainScene = new Scene(realRoot);

        primaryStage.setTitle("iMat");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.show();

        double w = mainScene.getWidth();
        double h = mainScene.getHeight();

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
        cart.setPrefHeight(h);
        cart.setMaxHeight(h);
        cart.setLayoutX(0);

        cartController.move_button.setText(toCheckout);
        double width = w-cart.getPrefWidth();

        storeFront.setPrefWidth(width);
        storeController.normalview.setPrefWidth(width);
        storeController.shoppingListView.setPrefWidth(width);
        cart.setMinWidth(cart.getPrefWidth());
        //Checkout
        FXMLLoader fxmlLoader_3 = new FXMLLoader();
        Pane checkOut = fxmlLoader_3.load(getClass().getResource("Checkout_NoCart.fxml").openStream());
        CheckOutController  checkOutController = (CheckOutController) fxmlLoader_3.getController();
        checkOut.setLayoutX(0);
        checkOut.setPrefHeight(h);
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


    public static void addPopUp(Pane n)
    {
        popup = n;
        panel = new Pane();
        panel.setPrefHeight(2000);
        panel.setPrefWidth(2000);

        n.setLayoutX(realRoot.getWidth()/2-n.getPrefWidth()/2);
        n.setLayoutY(realRoot.getHeight()/2-n.getPrefHeight()/2);

        realRoot.getChildren().add(panel);
        realRoot.getChildren().add(popup);
        panel.setOnMousePressed(e->removePopUp());

    }
    public static void removePopUp()
    {
        System.out.println(popup);
        if(popup!= null)
        {
            realRoot.getChildren().remove(popup);
            realRoot.getChildren().remove(panel);
        }
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

    public static void purchaseComplete()
    {
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
