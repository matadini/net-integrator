package pl.inzynier.netintegrator.desktop.gui.pane.mapping;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<RequestMethod> comboboxPublishMethod;

    @FXML
    private TextField textfieldTargetURL;

    @FXML
    private ComboBox<RequestMethod> comboboxTargetMethod;

    @FXML
    private TextField textfieldTargetServer;

    private final UrlMappingClient managmentClient;

    @FXML
   private void initialize() {

        try {
            ObservableList<RequestMethod> httpMethods = FXCollections.observableArrayList(RequestMethod.values());
            comboboxTargetMethod.setItems(httpMethods);
            comboboxPublishMethod.setItems(httpMethods);

            List<UrlMappingReadDto> all = managmentClient.findAll();
            ObservableList<UrlMappingReadDto> value = FXCollections.observableArrayList(all);
            tableViewUrlMapping.setItems(value);

            tableColumnUrlMapping.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().toString()));

        } catch (UrlMappingClientException e) {
            e.printStackTrace();
        }
    }
}
