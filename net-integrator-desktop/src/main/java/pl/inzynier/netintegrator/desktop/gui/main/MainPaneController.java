package pl.inzynier.netintegrator.desktop.gui.main;

import com.google.common.eventbus.EventBus;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.desktop.gui.mapping.UrlMappingPaneFactory;
import pl.inzynier.netintegrator.desktop.gui.script.ScriptPaneFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

@RequiredArgsConstructor
public class MainPaneController extends BorderPane {

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private TabPane tabPane;

    private final EventBus eventBus;

    private final ExecutorService executorService;

    private final UrlMappingClient managmentClient;

    private final ScriptClient scriptClient;
    @FXML
    private void initialize() {
        try {



            // mapping view
            BorderPane mappingController = UrlMappingPaneFactory.create(managmentClient, eventBus);

            // script view
            BorderPane scriptController = ScriptPaneFactory.create(managmentClient, scriptClient, eventBus);

            eventBus.register(scriptController);

            // fx pane
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.add(new Tab("Mapowania", mappingController));
            tabs.add(new Tab("Skrypty", scriptController));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
