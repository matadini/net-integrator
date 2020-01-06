package pl.inzynier.netintegrator.desktop.gui.main;

import com.google.common.eventbus.EventBus;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.client.user.UserClient;
import pl.inzynier.netintegrator.desktop.gui.mapping.UrlMappingPaneFactory;
import pl.inzynier.netintegrator.desktop.gui.script.ScriptPaneFactory;
import pl.inzynier.netintegrator.desktop.gui.user.UserPaneFactory;
import pl.inzynier.netintegrator.desktop.shared.CommonUtil;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

@RequiredArgsConstructor
public class MainPaneController extends BorderPane {

    @FXML
    private TabPane tabPane;

    private final EventBus eventBus;

    private final ExecutorService executorService;

    private final UrlMappingClient managmentClient;

    private final ScriptClient scriptClient;

    private final UserClient userClient;

    @FXML
    private void initialize() {
        try {


            //  Strings.pad

            // mapping view
            BorderPane mappingController = UrlMappingPaneFactory.create(managmentClient, eventBus);

            // script view
            BorderPane scriptController = ScriptPaneFactory.create(managmentClient, scriptClient, eventBus);
            eventBus.register(scriptController);

            // user view
            BorderPane userController = UserPaneFactory.create(userClient, eventBus);

            // fx pane
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.add(new Tab(CommonUtil.standardFormat( "Mappings"), mappingController));
            tabs.add(new Tab(CommonUtil.standardFormat( "Scripts"), scriptController));
            tabs.add(new Tab(CommonUtil.standardFormat( "Users"), userController));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
