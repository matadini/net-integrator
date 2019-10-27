package pl.inzynier.netintegrator.desktop.gui.pane.mapping;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;
import pl.inzynier.netintegrator.http.util.RequestMethod;

import java.net.URL;
import java.util.List;

@RequiredArgsConstructor
public class UrlMappingPane extends BorderPane {

    @FXML
    private TabPane tabPane;

    private final UrlMappingClient managmentClient;

    private UrlMappingPaneAdd mappingController;
    private UrlMappingPaneEdit mappingControllerEdit;

    @FXML
    private void initialize() {


        try {

            // dodawanie
            mappingController = new UrlMappingPaneAdd(managmentClient);
            mappingController = JavaFxUtil.loadFxml(mappingController, UrlMappingPaneAdd.class.getResource("UrlMappingPaneAdd.fxml"));


            Tab tabAdd = createNoClosableTab("       Add       ");
            tabAdd.setContent(mappingController);

            // edycja i usuwanie
            mappingControllerEdit = new UrlMappingPaneEdit(managmentClient);
            mappingControllerEdit = JavaFxUtil.loadFxml(mappingControllerEdit, UrlMappingPaneEdit.class.getResource("UrlMappingPaneEdit.fxml"));

            Tab tabEdit = createNoClosableTab(" Edit and remove ");
            tabEdit.setContent(mappingControllerEdit);

            // dodaj tabsy do widoku
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.add(tabAdd);
            tabs.add(tabEdit);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
//
//        try {
//            ObservableList<RequestMethod> httpMethods = FXCollections.observableArrayList(RequestMethod.values());
//            comboboxTargetMethod.setItems(httpMethods);
//            comboboxPublishMethod.setItems(httpMethods);
//
//            List<UrlMappingReadDto> all = managmentClient.findAll();
//            ObservableList<UrlMappingReadDto> value = FXCollections.observableArrayList(all);
//            tableViewUrlMapping.setItems(value);
//
//            tableColumnUrlMapping.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().toString()));
//
//        } catch (UrlMappingClientException e) {
//            e.printStackTrace();
//        }

    }

    private Tab createNoClosableTab(String test) {
        Tab tabAdd = new Tab(test);
        tabAdd.setClosable(false);
        return tabAdd;
    }
}

@RequiredArgsConstructor
class UrlMappingPaneEdit extends BorderPane {

    private final UrlMappingClient managmentClient;

}

@RequiredArgsConstructor
class UrlMappingPaneAdd extends BorderPane {

    @FXML
    private Button buttonAdd;

    @FXML
    private ComboBox<RequestMethod> comboboxPublishMethod;

    @FXML
    private ComboBox<RequestMethod> comboboxTargetMethod;

    @FXML
    private TextField textfieldPublishURL;

    @FXML
    private TextField textfieldTargetURL;

    @FXML
    private TextField textfieldTargetServer;

    private final UrlMappingClient managmentClient;


    @FXML
    private void initialize() {

    }
}
