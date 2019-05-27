package pl.inzynier.netintegrator.clientfx;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pl.inzynier.netintegrator.clientfx.shared.JavaFxUtil;
import pl.inzynier.netintegrator.clientfx.gui.ExamplePane;

import java.net.URL;


public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Screen primary = Screen.getPrimary();
        Rectangle2D primaryScreenBounds = primary.getVisualBounds();

        URL resource = ExamplePane.class.getResource("ExamplePane.fxml");
        ExamplePane controller = new ExamplePane();
        ExamplePane examplePane = JavaFxUtil.loadFxml(controller, resource);

        primaryStage.setScene(new Scene(examplePane));
        primaryStage.setWidth(primaryScreenBounds.getWidth()*0.75);
        primaryStage.setHeight(primaryScreenBounds.getHeight()*0.75);
        primaryStage.show();
    }
}
