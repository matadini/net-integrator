package pl.inzynier.netintegrator.desktop.gui.user;

import com.google.common.eventbus.EventBus;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.script.dto.ScriptClientException;
import pl.inzynier.netintegrator.client.user.UserClient;
import pl.inzynier.netintegrator.client.user.dto.UserClientException;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;


@RequiredArgsConstructor
class UserPaneAddController extends BorderPane {

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button buttonAdd;

    private UserPaneAddModel model;

    private final EventBus eventBus;

    private final UserClient userClient;


    @FXML
    public void initialize() {
        model = createModel();
        buttonAdd.setOnAction(this::onClickButtonNew);
    }

    private void onClickButtonNew(ActionEvent actionEvent) {
        try {
            UserWriteDTO dto = new UserWriteDTO(model.login.get(), model.password.get());
            userClient.create(dto);
            model = createModel();

            eventBus.post(UserPaneEvent.ADDED_USER);
        } catch (UserClientException e) {
            e.printStackTrace();
        }
    }


    private UserPaneAddModel createModel() {
        UserPaneAddModel model = new UserPaneAddModel();
        Bindings.bindBidirectional(textFieldLogin.textProperty(), model.login);
        Bindings.bindBidirectional(passwordField.textProperty(), model.password);
        return model;
    }

}

