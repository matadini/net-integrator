package pl.inzynier.netintegrator.desktop.gui.user;

import com.google.common.eventbus.EventBus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.user.UserClient;


@RequiredArgsConstructor
class UserPaneAddController extends BorderPane {

    @FXML
    private TextField textFieldPasswordConfirm;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button buttonAdd;

    @FXML
    public void initialize() {

    }
}

