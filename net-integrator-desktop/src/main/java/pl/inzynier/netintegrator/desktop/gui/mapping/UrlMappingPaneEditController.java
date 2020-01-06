package pl.inzynier.netintegrator.desktop.gui.mapping;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.*;
import pl.inzynier.netintegrator.desktop.shared.CommonStrings;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEvent;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;
import pl.inzynier.netintegrator.desktop.shared.event.SignalOnly;
import pl.inzynier.netintegrator.http.util.RequestMethod;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
class UrlMappingPaneEditController extends BorderPane {


    @FXML
    private ListView<UrlMappingReadDto> listView;

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
    private Label labelUrlMappingId;

    @FXML
    private ComboBox<RequestMethod> comboboxTargetMethod;

    private final UrlMappingClient mappingClient;
    private final EventBus eventBus;
    private UrlMappingPaneEditModel model;
    private final UrlMappingPaneAddModelToUrlMappingWriteDto mapper;

    @FXML
    private void initialize() {

        downloadUrlMappingList();

        listView.getSelectionModel().selectedItemProperty().addListener(this::onClickListView);
        buttonSave.setOnAction(this::onClickButtonSave);
        buttonRemove.setOnAction(this::onClickButtonRemove);
        model = createNewModel();

    }

    private void onClickButtonSave(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(CommonStrings.APP_NAME);
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) {

            try {
                UrlMappingWriteDto apply = mapper.apply(model);
                long urlMappingId = model.id.get();
                mappingClient.update(apply, urlMappingId);

                SignalOnly signalOnly = SignalOnly.of(ApplicationEventSignal.URL_MAPPING_REMOVE);
                eventBus.post(signalOnly);

                downloadUrlMappingList();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private void onClickListView(ObservableValue<? extends UrlMappingReadDto> observable, UrlMappingReadDto oldValue, UrlMappingReadDto newValue) {
        if (Objects.nonNull(newValue)) {
            fillModel(newValue);
        }
    }

    private void onClickButtonRemove(ActionEvent event) {
        MultipleSelectionModel<UrlMappingReadDto> selectionModel = listView.getSelectionModel();
        UrlMappingReadDto selectedItem = selectionModel.getSelectedItem();
        if (Objects.nonNull(selectedItem)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(CommonStrings.APP_NAME);;
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get().equals(ButtonType.OK)) {
                try {
                    mappingClient.delete(selectedItem.getUrlMappingId());
                    listView.getItems().remove(selectedItem);

                    SignalOnly signalOnly = SignalOnly.of(ApplicationEventSignal.URL_MAPPING_REMOVE);
                    eventBus.post(signalOnly);

                } catch (UrlMappingClientException e) {
                    e.printStackTrace();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Nie wybrano mappingu do usuniecia");
            alert.showAndWait();
        }
    }

    private void downloadUrlMappingList() {
        try {
            ObservableList<RequestMethod> httpMethods = FXCollections.observableArrayList(RequestMethod.values());
            comboboxTargetMethod.setItems(httpMethods);
            comboboxPublishMethod.setItems(httpMethods);

            List<UrlMappingReadDto> all = mappingClient.findAll();
            ObservableList<UrlMappingReadDto> value = FXCollections.observableArrayList(all);
            listView.setItems(value);

        } catch (UrlMappingClientException e) {
            e.printStackTrace();
        }
    }


    private void fillModel(UrlMappingReadDto dto) {
        @NonNull TargetEndpointDto target = dto.getTarget();
        model.targetURL.set(target.getMethodUrl());
        model.targetMethod.set(target.getMethod());
        model.targetServer.set(target.getHostAddress());

        @NonNull PublishEndpointDto endpoint = dto.getEndpoint();
        model.publishMethod.set(endpoint.getMethod());
        model.publishURL.set(endpoint.getMethodUrl());

        model.id.set(dto.getUrlMappingId());
    }

    /**
     * Tworzy model powiazany z kontrolkami
     *
     * @return
     */
    private UrlMappingPaneEditModel createNewModel() {
        UrlMappingPaneEditModel newModel = new UrlMappingPaneEditModel();
        Bindings.bindBidirectional(comboboxTargetMethod.valueProperty(), newModel.targetMethod);
        Bindings.bindBidirectional(textfieldTargetURL.textProperty(), newModel.targetURL);
        Bindings.bindBidirectional(textfieldTargetServer.textProperty(), newModel.targetServer);

        Bindings.bindBidirectional(comboboxPublishMethod.valueProperty(), newModel.publishMethod);
        Bindings.bindBidirectional(textfieldPublishURL.textProperty(), newModel.publishURL);
        Bindings.bindBidirectional(textfieldPublishURL.textProperty(), newModel.publishURL);

        Bindings.bindBidirectional(labelUrlMappingId.textProperty(), newModel.id, new NumberStringConverter());
        return newModel;
    }

    @Subscribe
    private void handle(ApplicationEvent event) {
        if (ApplicationEventSignal.URL_MAPPING_CREATE.equals(
                event.getType())) {
            downloadUrlMappingList();
        }
    }
}
