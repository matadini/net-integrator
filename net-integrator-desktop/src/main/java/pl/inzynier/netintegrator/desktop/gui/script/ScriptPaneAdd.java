package pl.inzynier.netintegrator.desktop.gui.script;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.client.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.client.script.dto.ScriptClientException;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;
import pl.inzynier.netintegrator.client.script.dto.ScriptWriteDto;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEvent;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;
import pl.inzynier.netintegrator.desktop.shared.event.SelectedUrlMappingChanged;

@RequiredArgsConstructor
class ScriptPaneAdd extends BorderPane {


    @FXML
    private Button buttonNew;

    @FXML
    private Label labelUrlMapping;

    @FXML
    private TextArea textFieldScriptContent;

    @FXML
    private ComboBox<ScriptType> comboboxScriptType;

    private ScriptPaneModel model;

    private final EventBus eventBus;

    private final ScriptClient scriptClient;

    @FXML
    public void initialize() {
        model = createModel();
        comboboxScriptType.setItems(FXCollections.observableArrayList(ScriptType.values()));

        buttonNew.setOnAction(this::onClickButtonNew);

    }

    private void onClickButtonNew(ActionEvent event) {


        try {
            ScriptWriteDto dto = new ScriptWriteDto();
            dto.setContent(model.content.get());
            dto.setScriptType(model.type.get());
            dto.setUrlMappingId(model.urlMappingId.get());

            Long aLong = scriptClient.create(dto);

            UrlMappingReadDto urlMapping = new UrlMappingReadDto(model.urlMappingId.get(), new PublishEndpointDto(), new TargetEndpointDto());
            SelectedUrlMappingChanged toPost = new SelectedUrlMappingChanged(urlMapping);
            eventBus.post(toPost);

        } catch (ScriptClientException e) {
            e.printStackTrace();
        }
    }

    private ScriptPaneModel createModel() {
        ScriptPaneModel model = new ScriptPaneModel();
        Bindings.bindBidirectional(labelUrlMapping.textProperty(), model.urlMappingId, new NumberStringConverter());
        Bindings.bindBidirectional(textFieldScriptContent.textProperty(), model.content);
        Bindings.bindBidirectional(comboboxScriptType.valueProperty(), model.type);
        return model;
    }

    @Subscribe
    private void handle(ApplicationEvent event) {

        if (ApplicationEventSignal.SELECTED_URL_MAPPING_CHANGED.equals(event.getType())) {
            SelectedUrlMappingChanged item = (SelectedUrlMappingChanged) event;
            model.urlMappingId.set(item.getUrlMapping().getUrlMappingId());
        }

    }

}
