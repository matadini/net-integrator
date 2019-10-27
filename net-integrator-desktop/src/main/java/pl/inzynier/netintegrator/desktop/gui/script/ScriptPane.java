package pl.inzynier.netintegrator.desktop.gui.script;

import com.google.common.eventbus.Subscribe;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;
import pl.inzynier.netintegrator.desktop.shared.ApplicationEvent;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ScriptPane extends BorderPane {


    @FXML
    private ComboBox<UrlMappingReadDto> comboboxUrlMappings;

    @FXML
    private TabPane tabPane;

    private final UrlMappingClient managmentClient;

    private final ScriptClient scriptClient;

    ScriptPaneAdd paneAdd;

    @FXML
    public void initialize() {


        downloadUrlMappings();

        SingleSelectionModel<UrlMappingReadDto> selectionModel = comboboxUrlMappings.getSelectionModel();
        ReadOnlyObjectProperty<UrlMappingReadDto> selectedItemProperty = selectionModel.selectedItemProperty();
        selectedItemProperty.addListener(this::onChangedComboboxUrlMapping);


        try {
            paneAdd = new ScriptPaneAdd(managmentClient, scriptClient);
            paneAdd = JavaFxUtil.loadFxml(paneAdd, ScriptPaneAdd.class.getResource("ScriptPaneAdd.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tab add = new Tab("Add");
        add.setContent(paneAdd);

        tabPane.getTabs().add(add);
        //  eventBus.register(paneEdit);


//        MultipleSelectionModel<ScriptReadDto> selectionModel1 = listViewScripts.getSelectionModel();
//        ReadOnlyObjectProperty<ScriptReadDto> scriptReadDtoReadOnlyObjectProperty = selectionModel1.selectedItemProperty();
//        scriptReadDtoReadOnlyObjectProperty.addListener(this::onChangedListViewScript);
//
//
//        comboboxScriptType.setItems(FXCollections.observableArrayList(ScriptType.values()));
//
//
//        buttonNew.setOnAction(this::onClickButtonNew);
//        buttonSave.setOnAction(this::onClickButtonSave);
//        buttonRemove.setOnAction(this::onClickButtonRemove);

    }

    @Subscribe
    private void handle(ApplicationEvent event) {

        if (ApplicationEvent.URL_MAPPING_CREATE.equals(event) || ApplicationEvent.URL_MAPPING_REMOVE.equals(event)) {
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


    private UrlMappingReadDto getSelectedUrlMappingReadDto() {
        SingleSelectionModel<UrlMappingReadDto> selectionModel = comboboxUrlMappings.getSelectionModel();
        return selectionModel.getSelectedItem();
    }

    private void onChangedComboboxUrlMapping(ObservableValue<? extends UrlMappingReadDto> observable, UrlMappingReadDto oldValue, UrlMappingReadDto newValue) {
        System.out.println("onChangedComboboxUrlMapping");

    }


}


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


//
//@RequiredArgsConstructor
//public class ScriptPaneAdd extends BorderPane {
//
//
//    @FXML
//    private ComboBox<UrlMappingReadDto> comboboxUrlMappings;
//
//    @FXML
//    private Button buttonNew;
//
//    @FXML
//    private Button buttonSave;
//
//    @FXML
//    private Button buttonRemove;
//
//    @FXML
//    private TextArea textFieldScriptContent;
//
//    @FXML
//    private ComboBox<ScriptType> comboboxScriptType;
//
//    @FXML
//    private ListView<ScriptReadDto> listViewScripts;
//
//    private final UrlMappingClient managmentClient;
//
//    private final ScriptClient scriptClient;
//
//    @FXML
//    public void initialize() {
//
//
//        try {
//            List<UrlMappingReadDto> all = managmentClient.findAll();
//            ObservableList<UrlMappingReadDto> value = FXCollections.observableArrayList(all);
//            comboboxUrlMappings.setItems(value);
//
//        } catch (Exception ex) {
//
//        }
//
//        SingleSelectionModel<UrlMappingReadDto> selectionModel = comboboxUrlMappings.getSelectionModel();
//        ReadOnlyObjectProperty<UrlMappingReadDto> selectedItemProperty = selectionModel.selectedItemProperty();
//        selectedItemProperty.addListener(this::onChangedComboboxUrlMapping);
//
//        MultipleSelectionModel<ScriptReadDto> selectionModel1 = listViewScripts.getSelectionModel();
//        ReadOnlyObjectProperty<ScriptReadDto> scriptReadDtoReadOnlyObjectProperty = selectionModel1.selectedItemProperty();
//        scriptReadDtoReadOnlyObjectProperty.addListener(this::onChangedListViewScript);
//
//
//        comboboxScriptType.setItems(FXCollections.observableArrayList(ScriptType.values()));
//
//
//        buttonNew.setOnAction(this::onClickButtonNew);
//        buttonSave.setOnAction(this::onClickButtonSave);
//        buttonRemove.setOnAction(this::onClickButtonRemove);
//
//    }
//
//    private void onChangedListViewScript(ObservableValue<? extends ScriptReadDto> observable, ScriptReadDto oldValue, ScriptReadDto newValue) {
//
//        System.out.println("elo 2");
//    }
//
//    private void onClickButtonRemove(ActionEvent event) {
//
//        try {
//            ScriptReadDto selectedItem = listViewScripts.getSelectionModel().getSelectedItem();
//            scriptClient.delete(selectedItem);
//
//            UrlMappingReadDto selectedUrlMappingReadDto = getSelectedUrlMappingReadDto();
//            onChangedComboboxUrlMapping(null, null, selectedUrlMappingReadDto);
//
//        } catch (Exception ex) {
//
//        }
//
//    }
//
//    private void onClickButtonSave(ActionEvent event) {
//
//    }
//
//    private void onClickButtonNew(ActionEvent event) {
//
//        try {
//            UrlMappingReadDto selectedUrlMappingReadDto = getSelectedUrlMappingReadDto();
//            Long urlMappingId = selectedUrlMappingReadDto.getUrlMappingId();
//            ScriptWriteDto dto = new ScriptWriteDto(urlMappingId, "placeholder", ScriptType.NONE);
//            scriptClient.create(dto);
//
//            onChangedComboboxUrlMapping(null, null, selectedUrlMappingReadDto);
//
//        } catch (ScriptClientException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private UrlMappingReadDto getSelectedUrlMappingReadDto() {
//        SingleSelectionModel<UrlMappingReadDto> selectionModel = comboboxUrlMappings.getSelectionModel();
//        return selectionModel.getSelectedItem();
//    }
//
//    private void onChangedComboboxUrlMapping(ObservableValue<? extends UrlMappingReadDto> observable, UrlMappingReadDto oldValue, UrlMappingReadDto newValue) {
//        System.out.println("elo");
//        //  labelDebug.setText(newValue.toString());
//
//        try {
//            Long urlMappingId = newValue.getUrlMappingId();
//            List<ScriptReadDto> byUrlMappingId = scriptClient.findByUrlMappingId(urlMappingId);
//            listViewScripts.setItems(FXCollections.observableArrayList(byUrlMappingId));
//
//        } catch (Exception ex) {
//
//        }
//
//    }
//
//
//}
