package pl.inzynier.netintegrator.desktop.gui.script;

import com.google.common.eventbus.Subscribe;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.client.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEvent;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;
import pl.inzynier.netintegrator.desktop.shared.event.SelectedUrlMappingChanged;
import pl.inzynier.netintegrator.http.util.RequestMethod;

import java.util.List;

@RequiredArgsConstructor
class ScriptPaneEdit extends BorderPane {

    @FXML
    private Label labelScriptId;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonRemove;

    @FXML
    private TextArea textFieldScriptContent;

    @FXML
    private ListView<ScriptReadDto> listViewScripts;

    @FXML
    private ComboBox<ScriptType> comboboxScriptType;

    private final UrlMappingClient managmentClient;

    private final ScriptClient scriptClient;

    private ScriptPaneModel model;

    @FXML
    public void initialize() {
        model = createModel();

        listViewScripts.getSelectionModel().selectedItemProperty().addListener(this::changed);

    }

    private void changed(ObservableValue<? extends ScriptReadDto> observable, ScriptReadDto oldValue, ScriptReadDto newValue) {
        model.scriptId.set(newValue.getScriptId());
        model.content.set(newValue.getContent());
        model.type.set(newValue.getScriptType());
    }



    private ScriptPaneModel createModel() {
        ScriptPaneModel model = new ScriptPaneModel();
        Bindings.bindBidirectional(labelScriptId.textProperty(), model.scriptId, new NumberStringConverter());
        Bindings.bindBidirectional(textFieldScriptContent.textProperty(), model.content);
        Bindings.bindBidirectional(comboboxScriptType.valueProperty(), model.type);
        return model;
    }
    private void download(Long urlMappingId) {

        try {
            List<ScriptReadDto> byUrlMappingId = scriptClient.findByUrlMappingId(urlMappingId);
            listViewScripts.setItems(FXCollections.observableArrayList(byUrlMappingId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Subscribe
    private void handle(ApplicationEvent event) {

        if (ApplicationEventSignal.SELECTED_URL_MAPPING_CHANGED.equals(event.getType())) {
            SelectedUrlMappingChanged item = (SelectedUrlMappingChanged) event;
            download(item.getUrlMappingId());
        }

    }

}
