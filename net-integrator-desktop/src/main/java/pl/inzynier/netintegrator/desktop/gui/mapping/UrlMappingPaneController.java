package pl.inzynier.netintegrator.desktop.gui.mapping;

import com.google.common.eventbus.EventBus;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.net.URL;

@RequiredArgsConstructor
class UrlMappingPaneController extends BorderPane {

    @FXML
    private TabPane tabPane;

    private final UrlMappingClient managmentClient;
    private final EventBus eventBus;

    private UrlMappingPaneAddController controllerAdd;
    private UrlMappingPaneEditController controllerEdit;

    private final UrlMappingPaneAddModelToUrlMappingWriteDto mapper;

    @FXML
    private void initialize() {


        try {

            // dodawanie
            controllerAdd = new UrlMappingPaneAddController(
                    managmentClient, eventBus, mapper);
            String path = "UrlMappingPaneAddController.fxml";
            URL resource = UrlMappingPaneAddController.class.getResource(path);
            controllerAdd = JavaFxUtil.loadFxml(controllerAdd, resource);


            Tab tabAdd = JavaFxUtil.createNoClosableTab("       Add       ");
            tabAdd.setContent(controllerAdd);

            // edycja i usuwanie
            controllerEdit = new UrlMappingPaneEditController(
                    managmentClient, eventBus, mapper);
            String name = "UrlMappingPaneEditController.fxml";
            URL resource1 = UrlMappingPaneEditController.class.getResource(name);
            controllerEdit = JavaFxUtil.loadFxml(controllerEdit, resource1);
            eventBus.register(controllerEdit);

            Tab tabEdit = JavaFxUtil.createNoClosableTab(" Edit and remove ");
            tabEdit.setContent(controllerEdit);

            // dodaj tabsy do widoku
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.add(tabAdd);
            tabs.add(tabEdit);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}

