package pl.inzynier.netintegrator.clientfx.gui;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pl.inzynier.netintegrator.clientfx.shared.JavaFxUtil;

import java.io.IOException;
import java.net.URL;

public class ExamplePane extends BorderPane {

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private void initialize() {
        try {
            URL resource = UrlMappingPane.class.getResource("UrlMappingPane.fxml");
            UrlMappingPane controller = UrlMappingPane.builder().build();
            UrlMappingPane urlMappingPane = JavaFxUtil.loadFxml(controller, resource);
            mainGridPane.add(urlMappingPane, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
