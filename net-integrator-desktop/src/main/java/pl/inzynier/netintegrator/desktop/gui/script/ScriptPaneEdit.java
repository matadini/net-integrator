package pl.inzynier.netintegrator.desktop.gui.script;

import com.google.common.eventbus.Subscribe;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.client.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEvent;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;
import pl.inzynier.netintegrator.desktop.shared.event.SelectedUrlMappingChanged;

import java.util.List;

//class Event {
//    Long scrip
//}

@RequiredArgsConstructor
class ScriptPaneEdit extends BorderPane {


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

    @FXML
    public void initialize() {

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

        System.out.println("eloszka");
        if (ApplicationEventSignal.SELECTED_URL_MAPPING_CHANGED.equals(event.getType())) {

            SelectedUrlMappingChanged item = (SelectedUrlMappingChanged) event;
            download(item.getUrlMappingId());
        }

    }

}
