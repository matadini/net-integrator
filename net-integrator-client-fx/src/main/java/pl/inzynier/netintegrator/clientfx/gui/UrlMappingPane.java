package pl.inzynier.netintegrator.clientfx.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.Builder;
import pl.inzynier.netintegrator.clientfx.managmentclient.ManagmentClient;

import java.util.Collections;
import java.util.Observable;

@Builder
public class UrlMappingPane extends BorderPane {

    ManagmentClient managmentClient;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonRemove;

    @FXML
    private ComboBox<HttpMethod> comboboxPublishMethod;

    @FXML
    private ComboBox<HttpMethod> comboboxTargetMethod;

    @FXML
    private TextField textfieldPublishURL;

    @FXML
    private TextField textfieldTargetURL;

    @FXML
    private TextField textfieldTargetServer;

    @FXML
    private void initialize() {

        ObservableList<HttpMethod> httpMethods = FXCollections.observableArrayList(HttpMethod.values());
        comboboxTargetMethod.setItems(httpMethods);
        comboboxPublishMethod.setItems(httpMethods);
    }
}
