package pl.inzynier.netintegrator.desktop.gui.pane.mapping;

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
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.mapping.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.client.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingWriteDto;
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

        try {
            UrlMappingWriteDto mappingDto = modelToWriteDto(model);
            managmentClient.create(mappingDto);

            System.out.println(model.toString());

            model = createNewModel();
        } catch (UrlMappingClientException e) {
            e.printStackTrace();
        }
    }

    private static UrlMappingWriteDto modelToWriteDto(UrlMappingPaneAddModel model) {
        TargetEndpointDto target = new TargetEndpointDto();
        target.setHostAddress(model.targetEndpointServer.get());
        target.setMethod(model.targetEndpointMethod.get());
        target.setMethodUrl(model.targetEndpointURL.get());

        PublishEndpointDto endpoint = new PublishEndpointDto();
        endpoint.setMethod(model.publishEndpointMethod.get());
        endpoint.setMethodUrl(model.publishEndpointURL.get());

        UrlMappingWriteDto mappingDto = new UrlMappingWriteDto();
        mappingDto.setEndpoint(endpoint);
        mappingDto.setTarget(target);
        return mappingDto;
    }
}
