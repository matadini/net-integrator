package pl.inzynier.netintegrator.desktop.gui.script;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;

@RequiredArgsConstructor
class ScriptPaneAdd extends BorderPane {


    @FXML
    private Button buttonNew;

    @FXML
    private TextArea textFieldScriptContent;

    @FXML
    private ComboBox<ScriptType> comboboxScriptType;

    private final UrlMappingClient managmentClient;

    private final ScriptClient scriptClient;

    @FXML
    public void initialize() {

    }


}
