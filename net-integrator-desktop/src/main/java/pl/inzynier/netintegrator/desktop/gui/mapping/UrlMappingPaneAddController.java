package pl.inzynier.netintegrator.desktop.gui.mapping;

import com.google.common.eventbus.EventBus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingWriteDto;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;
import pl.inzynier.netintegrator.desktop.shared.event.SignalOnly;
import pl.inzynier.netintegrator.http.util.RequestMethod;


@RequiredArgsConstructor
class UrlMappingPaneAddController extends BorderPane {


    @FXML
    private Label labelInfo;

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

    private final EventBus eventBus;

    private UrlMappingPaneAddModel model;

    private final UrlMappingPaneAddModelToUrlMappingWriteDto mapper;

    @FXML
    private void initialize() {

        ObservableList<RequestMethod> value = FXCollections.observableArrayList(RequestMethod.values());
        comboboxPublishMethod.setItems(value);
        comboboxTargetMethod.setItems(value);

        model = createNewModel();
        buttonAdd.setOnAction(this::onClickButtonAdd);

    }

    private UrlMappingPaneAddModel createNewModel() {
        UrlMappingPaneAddModel newModel =
                new UrlMappingPaneAddModel();

        comboboxTargetMethod.valueProperty()
                .bindBidirectional(newModel.targetMethod);

        textfieldTargetURL.textProperty()
                .bindBidirectional(newModel.targetURL);

        textfieldTargetServer.textProperty()
                .bindBidirectional(newModel.targetServer);

        comboboxPublishMethod.valueProperty()
                .bindBidirectional(newModel.publishMethod);

        textfieldPublishURL.textProperty()
                .bindBidirectional(newModel.publishURL);

        return newModel;
    }

    private void onClickButtonAdd(ActionEvent event) {

        try {
            // wyslij do serwera dane do zapisu
            UrlMappingWriteDto mappingDto = mapper.apply(model);
            managmentClient.create(mappingDto);

            // wyslij sygnal na szyne
            SignalOnly signalOnly = SignalOnly.of(
                    ApplicationEventSignal.URL_MAPPING_CREATE);
            eventBus.post(signalOnly);

            // wyczysc formularz
            model = createNewModel();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Dodano nowy URL Mapping");
            alert.showAndWait();
        } catch (UrlMappingClientException e) {
            e.printStackTrace();
        }
    }


}
