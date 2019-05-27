package pl.inzynier.netintegrator.clientfx.gui;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pl.inzynier.netintegrator.clientfx.managmentclient.ManagmentClientFactory;
import pl.inzynier.netintegrator.clientfx.shared.JavaFxUtil;

import java.io.IOException;
import java.net.URL;

public class MainPane extends BorderPane {

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private void initialize() {
        try {

            // view + controller
            URL resource = UrlMappingPane.class.getResource("UrlMappingPane.fxml");
            UrlMappingPane controller = UrlMappingPane.builder()
                    .managmentClient(ManagmentClientFactory.create("fake-adress"))
                    .build();

            // fx pane
            UrlMappingPane urlMappingPane = JavaFxUtil.loadFxml(controller, resource);
            mainGridPane.add(urlMappingPane, 0, 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
