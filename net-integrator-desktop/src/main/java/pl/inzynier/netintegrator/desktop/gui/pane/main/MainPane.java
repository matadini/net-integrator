package pl.inzynier.netintegrator.desktop.gui.pane.main;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.desktop.gui.pane.mapping.UrlMappingPane;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

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

            UrlMappingClient managmentClient = UrlMappingClient.create("fake-adress");

            // view + controller
            URL resource = UrlMappingPane.class.getResource("UrlMappingPane.fxml");
            UrlMappingPane controller = UrlMappingPane.builder()
                    .managmentClient(managmentClient)
                    .build();

            // fx pane
            UrlMappingPane urlMappingPane = JavaFxUtil.loadFxml(controller, resource);
            mainGridPane.add(urlMappingPane, 0, 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
