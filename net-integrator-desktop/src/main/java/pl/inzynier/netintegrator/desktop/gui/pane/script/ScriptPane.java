package pl.inzynier.netintegrator.desktop.gui.pane.script;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;

import java.util.List;

@RequiredArgsConstructor
public class ScriptPane extends BorderPane {


    @FXML
    private ComboBox<UrlMappingReadDto> comboboxUrlMappings;

    @FXML
    private Label labelDebug;

    private final UrlMappingClient managmentClient;

    @FXML
    public void initialize() {


        try {
            List<UrlMappingReadDto> all = managmentClient.findAll();
            ObservableList<UrlMappingReadDto> value = FXCollections.observableArrayList(all);
            comboboxUrlMappings.setItems(value);

            SingleSelectionModel<UrlMappingReadDto> selectionModel = comboboxUrlMappings.getSelectionModel();
            ReadOnlyObjectProperty<UrlMappingReadDto> selectedItemProperty = selectionModel.selectedItemProperty();
            selectedItemProperty.addListener(this::onChangedComboboxUrlMapping);




        } catch (Exception ex) {

        }
    }

    public void onChangedComboboxUrlMapping(ObservableValue<? extends UrlMappingReadDto> observable, UrlMappingReadDto oldValue, UrlMappingReadDto newValue) {
        System.out.println("elo");
        labelDebug.setText(newValue.toString());
    }


}
