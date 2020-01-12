package pl.inzynier.netintegrator.desktop.gui.script;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.client.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.desktop.shared.CommonUtil;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;
import pl.inzynier.netintegrator.desktop.shared.RequestMethodStringConverter;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEvent;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;
import pl.inzynier.netintegrator.desktop.shared.event.SelectedUrlMappingChanged;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor
class ScriptPaneController extends BorderPane {

    @FXML
    private Label labelId;

    @FXML
    private Label labelPublishMethod;

    @FXML
    private Label labelPublishURL;

    @FXML
    private Label labelTargetAddress;

    @FXML
    private Label labelTargetMethod;

    @FXML
    private Label labelTargetURL;

    @FXML
    private TabPane tabPane;

    @FXML
    private ComboBox<UrlMappingReadDto> comboboxUrlMappings;

    private ScriptPaneAdd paneAdd;

    private ScriptPaneEdit paneEdit;

    private final UrlMappingClient managmentClient;

    private final ScriptClient scriptClient;

    private final EventBus eventBus;

    private final RequestMethodStringConverter converter = new RequestMethodStringConverter();

    private ScriptPaneModelUrlMapping model;

    private ScriptPaneModelUrlMapping createModel() {
        ScriptPaneModelUrlMapping model = new ScriptPaneModelUrlMapping();
        Bindings.bindBidirectional(labelId.textProperty(), model.urlMappingId, new NumberStringConverter());
        Bindings.bindBidirectional(labelPublishURL.textProperty(), model.publishEndpointURL);
        Bindings.bindBidirectional(labelPublishMethod.textProperty(), model.publishEndpointMethod, converter);
        Bindings.bindBidirectional(labelTargetURL.textProperty(), model.targetEndpointURL);
        Bindings.bindBidirectional(labelTargetMethod.textProperty(), model.targetEndpointMethod, converter);
        Bindings.bindBidirectional(labelTargetAddress.textProperty(), model.targetEndpointServer);
        return model;
    }

    @FXML
    public void initialize() {

        model = createModel();
        downloadUrlMappings();

        SingleSelectionModel<UrlMappingReadDto> selectionModel = comboboxUrlMappings.getSelectionModel();
        ReadOnlyObjectProperty<UrlMappingReadDto> selectedItemProperty = selectionModel.selectedItemProperty();
        selectedItemProperty.addListener(this::onChangedComboboxUrlMapping);


        try {

            paneAdd = JavaFxUtil.loadFxml(
                    new ScriptPaneAdd(eventBus, scriptClient),
                    ScriptPaneAdd.class.getResource("ScriptPaneAdd.fxml"));
            eventBus.register(paneAdd);

            paneEdit = JavaFxUtil.loadFxml(
                    new ScriptPaneEdit(scriptClient),
                    ScriptPaneEdit.class.getResource("ScriptPaneEdit.fxml"));
            eventBus.register(paneEdit);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Tab add = JavaFxUtil.createNoClosableTab(CommonUtil.standardFormat("Add"));
        add.setContent(paneAdd);

        Tab edit = JavaFxUtil.createNoClosableTab(CommonUtil.standardFormat("Edit"));
        edit.setContent(paneEdit);

        ObservableList<Tab> tabs = tabPane.getTabs();
        tabs.add(add);
        tabs.add(edit);
    }

    @Subscribe
    private void handle(ApplicationEvent event) {

        if (ApplicationEventSignal.URL_MAPPING_CREATE.equals(event.getType())
                || ApplicationEventSignal.URL_MAPPING_REMOVE.equals(event.getType())) {
            downloadUrlMappings();
        }

    }

    private void downloadUrlMappings() {
        try {
            List<UrlMappingReadDto> all = managmentClient.findAll();
            ObservableList<UrlMappingReadDto> value = FXCollections.observableArrayList(all);
            comboboxUrlMappings.setItems(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void onChangedComboboxUrlMapping(ObservableValue<? extends UrlMappingReadDto> observable, UrlMappingReadDto oldValue, UrlMappingReadDto newValue) {

        if (Objects.nonNull(newValue)) {
            SelectedUrlMappingChanged event = new SelectedUrlMappingChanged(newValue);
            eventBus.post(event);
            fillModel(newValue);
        }

    }

    private void fillModel(UrlMappingReadDto newValue) {

        model.urlMappingId.set(newValue.getUrlMappingId());

        @NonNull PublishEndpointDto endpoint = newValue.getEndpoint();
        model.publishEndpointURL.set(endpoint.getMethodUrl());
        model.publishEndpointMethod.set(endpoint.getMethod());

        @NonNull TargetEndpointDto target = newValue.getTarget();
        model.targetEndpointURL.set(target.getMethodUrl());
        model.targetEndpointMethod.set(target.getMethod());
        model.targetEndpointServer.set(target.getHostAddress());
    }


}

