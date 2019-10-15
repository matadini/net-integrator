package pl.inzynier.netintegrator.desktop.gui.pane.main;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    private TabPane tabPane;

    @FXML
    private void initialize() {
        try {


            // view + controller
            UrlMappingClient managmentClient = UrlMappingClient.create("fake-adress");
            URL resource = UrlMappingPane.class.getResource("UrlMappingPane.fxml");
            UrlMappingPane controller = UrlMappingPane.builder()
                    .managmentClient(managmentClient)
                    .build();
            UrlMappingPane urlMappingPane = JavaFxUtil.loadFxml(controller, resource);

            // fx pane
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.add(new Tab("UrlMapping", urlMappingPane));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
