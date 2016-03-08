package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;

/**
 * @author Daniel.
 */
public class nameListView extends Pane
{
    public nameListView ()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane pane = fxmlLoader.load(getClass().getResource("nameList.fxml").openStream());
            getChildren().add(pane);
            setPrefHeight(pane.getPrefHeight());
            setPrefWidth(pane.getPrefWidth());
        }
        catch (IOException ex)
        {
            System.out.println("couldn't load it yoo. // nameListView");
        }
    }
}
