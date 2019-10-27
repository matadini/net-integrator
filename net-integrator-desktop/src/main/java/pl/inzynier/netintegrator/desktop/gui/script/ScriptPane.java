package pl.inzynier.netintegrator.desktop.gui.script;

import com.google.common.eventbus.EventBus;
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
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;
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

    private final EventBus eventBus;

    private ScriptPaneAdd paneAdd;

    private ScriptPaneEdit paneEdit;

    @FXML
    public void initialize() {


        downloadUrlMappings();

        SingleSelectionModel<UrlMappingReadDto> selectionModel = comboboxUrlMappings.getSelectionModel();
        ReadOnlyObjectProperty<UrlMappingReadDto> selectedItemProperty = selectionModel.selectedItemProperty();
        selectedItemProperty.addListener(this::onChangedComboboxUrlMapping);


        try {

            paneAdd = JavaFxUtil.loadFxml(
                    new ScriptPaneAdd(managmentClient, scriptClient),
                    ScriptPaneAdd.class.getResource("ScriptPaneAdd.fxml"));

            paneEdit = JavaFxUtil.loadFxml(
                    new ScriptPaneEdit(managmentClient, scriptClient),
                    ScriptPaneEdit.class.getResource("ScriptPaneEdit.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Tab add = JavaFxUtil.createNoClosableTab("      Add      ");
        add.setContent(paneAdd);

        Tab edit = JavaFxUtil.createNoClosableTab("Edit and remove");
        edit.setContent(paneEdit);
        eventBus.post(edit);

        ObservableList<Tab> tabs = tabPane.getTabs();
        tabs.add(add);
        tabs.add(edit);
    }

    @Subscribe
    private void handle(ApplicationEventSignal event) {

        if (ApplicationEventSignal.URL_MAPPING_CREATE.equals(event) || ApplicationEventSignal.URL_MAPPING_REMOVE.equals(event)) {
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

