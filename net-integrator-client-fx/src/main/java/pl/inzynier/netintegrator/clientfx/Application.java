package pl.inzynier.netintegrator.clientfx;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.inzynier.netintegrator.clientfx.shared.JavaFxUtil;
import pl.inzynier.netintegrator.clientfx.example.ExamplePane;

import java.net.URL;


public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL resource = ExamplePane.class.getResource("ExamplePane.fxml");
        ExamplePane controller = new ExamplePane();
        ExamplePane examplePane = JavaFxUtil.loadFxml(controller, resource);

        primaryStage.setScene(new Scene(examplePane));
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }
}
