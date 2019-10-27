package pl.inzynier.netintegrator.desktop.gui.pane.mapping;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.http.util.RequestMethod;

@RequiredArgsConstructor
class UrlMappingPaneAdd extends BorderPane {

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


    @FXML
    private void initialize() {

    }
}
