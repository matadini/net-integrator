package pl.inzynier.netintegrator.desktop.gui.login;

import com.google.common.eventbus.EventBus;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.client.user.UserClient;
import pl.inzynier.netintegrator.client.user.dto.UserClientException;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;
import pl.inzynier.netintegrator.desktop.shared.ApplicationSession;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;
import pl.inzynier.netintegrator.desktop.shared.event.SignalOnly;


@RequiredArgsConstructor
public class LoginPaneController extends BorderPane {


    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonLogin;

    private final EventBus eventBus;

    private final UserClient userClient;

    private final LoginPaneModel model = new LoginPaneModel();

    @FXML
    void initialize() {
        buttonExit.setOnAction(this::onClickButtonExit);
        buttonLogin.setOnAction(this::onClickButtonLogin);

        model.getLogin().bindBidirectional(textFieldLogin.textProperty());
        model.getPassword().bindBidirectional(textFieldPassword.textProperty());
    }

    private void onClickButtonLogin(ActionEvent event) {

        try {
            String login = model.getLogin().get();
            String password = model.getPassword().get();
            UserWriteDTO writeDTO = new UserWriteDTO(login, password);
            boolean authorization = userClient.authorization(writeDTO);
            if(authorization) {

                ApplicationSession instance = ApplicationSession.getInstance();
                instance.setLoggedUser(login);
                SignalOnly signalOnly = SignalOnly.of(ApplicationEventSignal.LOGIN_SUCCESS);
                eventBus.post(signalOnly);

            } else {
                Platform.runLater(this::showAlertNieprawidloweDaneLogowania);
            }
            Logger.info(model);
        } catch (UserClientException e) {
            e.printStackTrace();
        }

    }

    private void showAlertNieprawidloweDaneLogowania() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Nieprawidłowe dane logowania");
        alert.showAndWait();
    }

    private void onClickButtonExit(ActionEvent event) {
        SignalOnly signalOnly = SignalOnly.of(ApplicationEventSignal.EXIT);
        eventBus.post(signalOnly);
    }
}
