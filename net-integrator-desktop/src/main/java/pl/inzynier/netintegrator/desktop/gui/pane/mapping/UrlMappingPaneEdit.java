package pl.inzynier.netintegrator.desktop.gui.pane.mapping;

import com.google.common.eventbus.Subscribe;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.http.util.RequestMethod;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
class UrlMappingPaneEdit extends BorderPane {

    @FXML
    private TableView<UrlMappingReadDto> tableViewUrlMapping;

    @FXML
    private TableColumn<UrlMappingReadDto, String> tableColumnUrlMapping;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonRemove;

    @FXML
    private TextField textfieldPublishURL;

    @FXML
    private TextField textfieldTargetURL;

    @FXML
    private TextField textfieldTargetServer;

    @FXML
    private ComboBox<RequestMethod> comboboxPublishMethod;

    @FXML
    private ComboBox<RequestMethod> comboboxTargetMethod;

    private final UrlMappingClient mappingClient;

    @FXML
    private void initialize() {
        downloadUrlMappingList();

        buttonSave.setOnAction(this::onClickButtonSave);
        buttonRemove.setOnAction(this::onClickButtonRemove);

    }

    private void onClickButtonSave(ActionEvent event) {

    }

    private void onClickButtonRemove(ActionEvent event) {
        TableView.TableViewSelectionModel<UrlMappingReadDto> selectionModel = tableViewUrlMapping.getSelectionModel();
        UrlMappingReadDto selectedItem = selectionModel.getSelectedItem();
        if (Objects.nonNull(selectedItem)) {

            try {
                mappingClient.deactivate(selectedItem.getUrlMappingId());
                tableViewUrlMapping.getItems().remove(selectedItem);
            } catch (UrlMappingClientException e) {
                e.printStackTrace();
            }

        } else {

        }
    }

    private void downloadUrlMappingList() {
        try {
            ObservableList<RequestMethod> httpMethods = FXCollections.observableArrayList(RequestMethod.values());
            comboboxTargetMethod.setItems(httpMethods);
            comboboxPublishMethod.setItems(httpMethods);

            List<UrlMappingReadDto> all = mappingClient.findAll();
            ObservableList<UrlMappingReadDto> value = FXCollections.observableArrayList(all);
            tableViewUrlMapping.setItems(value);

            tableColumnUrlMapping.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().toString()));

        } catch (UrlMappingClientException e) {
            e.printStackTrace();
        }
    }


    @Subscribe
    private void handle(UrlMappingPaneEvent event) {
        if (UrlMappingPaneEvent.URL_MAPPING_CREATE.equals(event)) {
            downloadUrlMappingList();
        }
    }
}
