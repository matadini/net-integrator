package pl.inzynier.netintegrator.clientfx;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pl.inzynier.netintegrator.clientfx.gui.MainPane;
import pl.inzynier.netintegrator.clientfx.shared.JavaFxUtil;

import java.net.URL;


public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Screen primary = Screen.getPrimary();
        Rectangle2D primaryScreenBounds = primary.getVisualBounds();

        URL resource = MainPane.class.getResource("MainPane.fxml");
        MainPane controller = new MainPane();
        MainPane mainPane = JavaFxUtil.loadFxml(controller, resource);

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setWidth(primaryScreenBounds.getWidth()*0.75);
        primaryStage.setHeight(primaryScreenBounds.getHeight()*0.75);
        primaryStage.show();
    }
}
