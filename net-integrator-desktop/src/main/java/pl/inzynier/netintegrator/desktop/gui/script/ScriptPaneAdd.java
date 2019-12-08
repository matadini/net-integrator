package pl.inzynier.netintegrator.desktop.gui.script;

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
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;
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

    private final UrlMappingClient managmentClient;

    private final ScriptClient scriptClient;

    private ScriptPaneModel model;

    @FXML
    public void initialize() {
        model = createModel();
        comboboxScriptType.setItems(FXCollections.observableArrayList(ScriptType.values()));

        buttonNew.setOnAction(this::onClickButtonNew);

    }

    private void onClickButtonNew(ActionEvent event) {
        System.out.println(model);
    }

    private ScriptPaneModel createModel() {
        ScriptPaneModel model = new ScriptPaneModel();
        //  Bindings.bindBidirectional(labelScriptId.textProperty(), model.scriptId, new NumberStringConverter());
        Bindings.bindBidirectional(textFieldScriptContent.textProperty(), model.content);
        Bindings.bindBidirectional(comboboxScriptType.valueProperty(), model.type);
        return model;
    }

    @Subscribe
    private void handle(ApplicationEvent event) {

        if (ApplicationEventSignal.SELECTED_URL_MAPPING_CHANGED.equals(event.getType())) {
            SelectedUrlMappingChanged item = (SelectedUrlMappingChanged) event;

            System.out.println("AddScriptPane: " + item.getUrlMapping());
            //  item.get
            // download(item.getUrlMappingId());
        }

    }

}
