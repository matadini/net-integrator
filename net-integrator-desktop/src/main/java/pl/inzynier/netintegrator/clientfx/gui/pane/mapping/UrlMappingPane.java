package pl.inzynier.netintegrator.clientfx.gui.pane.mapping;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import lombok.Builder;
import pl.inzynier.netintegrator.clientfx.managmentclient.ManagmentClient;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.ManagmentClientException;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.UrlMappingRead;

import java.util.List;


public class UrlMappingPane extends BorderPane {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonRemove;

    @FXML
    private ComboBox<HttpMethod> comboboxPublishMethod;

    @FXML
    private ComboBox<HttpMethod> comboboxTargetMethod;

    @FXML
    private TextField textfieldPublishURL;

    @FXML
    private TextField textfieldTargetURL;

    @FXML
    private TextField textfieldTargetServer;

    @FXML
    private TableView<UrlMappingRead> tableViewUrlMapping;

    @FXML
    private TableColumn<UrlMappingRead, String> tableColumnUrlMapping;

    private final ManagmentClient managmentClient;

    @Builder
    UrlMappingPane(ManagmentClient managmentClient) {
        this.managmentClient = managmentClient;
    }

    @FXML
    private void initialize() {

        try {
            ObservableList<HttpMethod> httpMethods = FXCollections.observableArrayList(HttpMethod.values());
            comboboxTargetMethod.setItems(httpMethods);
            comboboxPublishMethod.setItems(httpMethods);

            List<UrlMappingRead> all = managmentClient.getAll();
            ObservableList<UrlMappingRead> value = FXCollections.observableArrayList(all);
            tableViewUrlMapping.setItems(value);

            tableColumnUrlMapping.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().toString()));

        } catch (ManagmentClientException e) {
            e.printStackTrace();
        }

    }
}
