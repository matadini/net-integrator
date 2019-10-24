package pl.inzynier.netintegrator.desktop.gui.pane.main;

import com.google.common.eventbus.EventBus;
import com.sun.javafx.text.ScriptMapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.desktop.gui.pane.mapping.UrlMappingPane;
import pl.inzynier.netintegrator.desktop.gui.pane.script.ScriptPane;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;

@RequiredArgsConstructor
public class MainPane extends BorderPane {

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private TabPane tabPane;

    private final EventBus eventBus;

    private final ExecutorService executorService;

    @FXML
    private void initialize() {
        try {

            // clients
            UrlMappingClient managmentClient = UrlMappingClient.create("fake-adress");
            ScriptClient scriptClient = ScriptClient.create("fake-address");

            // mapping view
            URL mappingFxmlUrl = UrlMappingPane.class.getResource("UrlMappingPane.fxml");
            UrlMappingPane mappingController = new UrlMappingPane(managmentClient);
            mappingController = JavaFxUtil.loadFxml(mappingController, mappingFxmlUrl);

            // script view
            URL scriptFxmlUrl = ScriptPane.class.getResource("ScriptPane.fxml");
            ScriptPane scriptController = new ScriptPane(managmentClient, scriptClient);
            scriptController = JavaFxUtil.loadFxml(scriptController, scriptFxmlUrl);

            // fx pane
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.add(new Tab("Mapowania", mappingController));
            tabs.add(new Tab("Skrypty", scriptController));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
