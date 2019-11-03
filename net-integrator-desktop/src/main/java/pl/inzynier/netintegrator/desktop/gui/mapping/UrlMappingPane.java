package pl.inzynier.netintegrator.desktop.gui.mapping;

import com.google.common.eventbus.EventBus;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

@RequiredArgsConstructor
public class UrlMappingPane extends BorderPane {

    @FXML
    private TabPane tabPane;

    private final UrlMappingClient managmentClient;
    private final EventBus eventBus;

    private UrlMappingPaneAdd paneAdd;
    private UrlMappingPaneEdit paneEdit;

    @FXML
    private void initialize() {


        try {

            UrlMappingPaneAddModelToUrlMappingWriteDto mapper = new UrlMappingPaneAddModelToUrlMappingWriteDto();

            // dodawanie
            paneAdd = new UrlMappingPaneAdd(managmentClient, eventBus, mapper);
            paneAdd = JavaFxUtil.loadFxml(paneAdd, UrlMappingPaneAdd.class.getResource("UrlMappingPaneAdd.fxml"));


            Tab tabAdd = JavaFxUtil.createNoClosableTab("       Add       ");
            tabAdd.setContent(paneAdd);

            // edycja i usuwanie
            paneEdit = new UrlMappingPaneEdit(managmentClient,eventBus, mapper);
            paneEdit = JavaFxUtil.loadFxml(paneEdit, UrlMappingPaneEdit.class.getResource("UrlMappingPaneEdit.fxml"));
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

