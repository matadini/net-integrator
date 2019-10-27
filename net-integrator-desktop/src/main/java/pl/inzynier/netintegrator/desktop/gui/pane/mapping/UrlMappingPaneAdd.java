package pl.inzynier.netintegrator.desktop.gui.pane.mapping;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.http.util.RequestMethod;

@RequiredArgsConstructor
class UrlMappingPaneAdd extends BorderPane {


    @FXML
    private Label labelInfo;

    @FXML
    private Button buttonAdd;

    @FXML
    private ComboBox<RequestMethod> comboboxPublishMethod;

    @FXML
    private ComboBox<RequestMethod> comboboxTargetMethod;

    @FXML
    private TextField textfieldPublishURL;

    @FXML
    private TextField textfieldTargetURL;

    @FXML
    private TextField textfieldTargetServer;

    private final UrlMappingClient managmentClient;

    private UrlMappingPaneAddModel model;

    @FXML
    private void initialize() {

        ObservableList<RequestMethod> value = FXCollections.observableArrayList(RequestMethod.values());
        comboboxPublishMethod.setItems(value);
        comboboxTargetMethod.setItems(value);

        model = createNewModel();
        buttonAdd.setOnAction(this::onClickButtonAdd);

    }

    private UrlMappingPaneAddModel createNewModel() {
        UrlMappingPaneAddModel newModel = new UrlMappingPaneAddModel();
        comboboxTargetMethod.valueProperty().bindBidirectional(newModel.targetEndpointMethod);
        textfieldTargetURL.textProperty().bindBidirectional(newModel.targetEndpointURL);
        textfieldTargetServer.textProperty().bindBidirectional(newModel.targetEndpointServer);

        comboboxPublishMethod.valueProperty().bindBidirectional(newModel.publishEndpointMethod);
        textfieldPublishURL.textProperty().bindBidirectional(newModel.publishEndpointURL);
        return newModel;
    }

    private void onClickButtonAdd(ActionEvent event) {

        System.out.println(model.toString());
        model = createNewModel();
    }
}
