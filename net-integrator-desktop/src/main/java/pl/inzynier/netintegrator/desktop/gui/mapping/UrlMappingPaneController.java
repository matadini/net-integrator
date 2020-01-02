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

@RequiredArgsConstructor
class UrlMappingPaneController extends BorderPane {

    @FXML
    private TabPane tabPane;

    private final UrlMappingClient managmentClient;
    private final EventBus eventBus;

    private UrlMappingPaneAddController paneAdd;
    private UrlMappingPaneEditController paneEdit;

    private final UrlMappingPaneAddModelToUrlMappingWriteDto mapper;

    @FXML
    private void initialize() {


        try {

            // dodawanie
            paneAdd = new UrlMappingPaneAddController(managmentClient, eventBus, mapper);
            paneAdd = JavaFxUtil.loadFxml(paneAdd, UrlMappingPaneAddController.class.getResource("UrlMappingPaneAddController.fxml"));


            Tab tabAdd = JavaFxUtil.createNoClosableTab("       Add       ");
            tabAdd.setContent(paneAdd);

            // edycja i usuwanie
            paneEdit = new UrlMappingPaneEditController(managmentClient, eventBus, mapper);
            paneEdit = JavaFxUtil.loadFxml(paneEdit, UrlMappingPaneEditController.class.getResource("UrlMappingPaneEditController.fxml"));
            eventBus.register(paneEdit);

            Tab tabEdit = JavaFxUtil.createNoClosableTab(" Edit and remove ");
            tabEdit.setContent(paneEdit);

            // dodaj tabsy do widoku
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.add(tabAdd);
            tabs.add(tabEdit);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}

