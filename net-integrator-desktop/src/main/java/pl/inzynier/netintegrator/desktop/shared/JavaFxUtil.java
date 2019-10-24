package pl.inzynier.netintegrator.desktop.shared;

import javafx.fxml.FXMLLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URL;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaFxUtil {

    public static <T> T loadFxml(T controller, URL resource) throws IOException {

        FXMLLoader loader = new FXMLLoader(resource);
        loader.setRoot(controller);
        loader.setController(controller);
        loader.load();
        return controller;
    }
}