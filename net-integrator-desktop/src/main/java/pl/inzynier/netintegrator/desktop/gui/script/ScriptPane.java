package pl.inzynier.netintegrator.desktop.gui.script;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.client.script.dto.ScriptClientException;
import pl.inzynier.netintegrator.client.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;
import pl.inzynier.netintegrator.client.script.dto.ScriptWriteDto;

import java.util.List;

@RequiredArgsConstructor
public class ScriptPane extends BorderPane {


    @FXML
    private ComboBox<UrlMappingReadDto> comboboxUrlMappings;

    @FXML
    private Button buttonNew;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonRemove;

    @FXML
    private TextArea textFieldScriptContent;

    @FXML
    private ComboBox<ScriptType> comboboxScriptType;

    @FXML
    private ListView<ScriptReadDto> listViewScripts;

    private final UrlMappingClient managmentClient;

    private final ScriptClient scriptClient;

    @FXML
    public void initialize() {


        try {
            List<UrlMappingReadDto> all = managmentClient.findAll();
            ObservableList<UrlMappingReadDto> value = FXCollections.observableArrayList(all);
            comboboxUrlMappings.setItems(value);

        } catch (Exception ex) {

        }

        SingleSelectionModel<UrlMappingReadDto> selectionModel = comboboxUrlMappings.getSelectionModel();
        ReadOnlyObjectProperty<UrlMappingReadDto> selectedItemProperty = selectionModel.selectedItemProperty();
        selectedItemProperty.addListener(this::onChangedComboboxUrlMapping);

        MultipleSelectionModel<ScriptReadDto> selectionModel1 = listViewScripts.getSelectionModel();
        ReadOnlyObjectProperty<ScriptReadDto> scriptReadDtoReadOnlyObjectProperty = selectionModel1.selectedItemProperty();
        scriptReadDtoReadOnlyObjectProperty.addListener(this::onChangedListViewScript);


        comboboxScriptType.setItems(FXCollections.observableArrayList(ScriptType.values()));


        buttonNew.setOnAction(this::onClickButtonNew);
        buttonSave.setOnAction(this::onClickButtonSave);
        buttonRemove.setOnAction(this::onClickButtonRemove);

    }

    private void onChangedListViewScript(ObservableValue<? extends ScriptReadDto> observable, ScriptReadDto oldValue, ScriptReadDto newValue) {

        System.out.println("elo 2");
    }

    private void onClickButtonRemove(ActionEvent event) {

        try {
            ScriptReadDto selectedItem = listViewScripts.getSelectionModel().getSelectedItem();
            scriptClient.delete(selectedItem);

            UrlMappingReadDto selectedUrlMappingReadDto = getSelectedUrlMappingReadDto();
            onChangedComboboxUrlMapping(null, null, selectedUrlMappingReadDto);

        } catch (Exception ex) {

        }

    }

    private void onClickButtonSave(ActionEvent event) {

    }

    private void onClickButtonNew(ActionEvent event) {

        try {
            UrlMappingReadDto selectedUrlMappingReadDto = getSelectedUrlMappingReadDto();
            Long urlMappingId = selectedUrlMappingReadDto.getUrlMappingId();
            ScriptWriteDto dto = new ScriptWriteDto(urlMappingId, "placeholder", ScriptType.NONE);
            scriptClient.create(dto);

            onChangedComboboxUrlMapping(null, null, selectedUrlMappingReadDto);

        } catch (ScriptClientException e) {
            e.printStackTrace();
        }

    }

    private UrlMappingReadDto getSelectedUrlMappingReadDto() {
        SingleSelectionModel<UrlMappingReadDto> selectionModel = comboboxUrlMappings.getSelectionModel();
        return selectionModel.getSelectedItem();
    }

    private void onChangedComboboxUrlMapping(ObservableValue<? extends UrlMappingReadDto> observable, UrlMappingReadDto oldValue, UrlMappingReadDto newValue) {
        System.out.println("elo");
        //  labelDebug.setText(newValue.toString());

        try {
            Long urlMappingId = newValue.getUrlMappingId();
            List<ScriptReadDto> byUrlMappingId = scriptClient.findByUrlMappingId(urlMappingId);
            listViewScripts.setItems(FXCollections.observableArrayList(byUrlMappingId));

        } catch (Exception ex) {

        }

    }


}
