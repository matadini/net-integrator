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
import pl.inzynier.netintegrator.client.login.LoginClient;
import pl.inzynier.netintegrator.desktop.Application;


@RequiredArgsConstructor
public class LoginPane extends BorderPane {


    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonLogin;

    private final EventBus eventBus;

    private final LoginClient loginClient;

    private final LoginPaneModel model = new LoginPaneModel();

    @FXML
    void initialize() {
        buttonExit.setOnAction(this::onClickButtonExit);
        buttonLogin.setOnAction(this::onClickButtonLogin);

        model.getLogin().bindBidirectional(textFieldLogin.textProperty());
        model.getPassword().bindBidirectional(textFieldPassword.textProperty());
    }

    private void onClickButtonLogin(ActionEvent event) {

        boolean authorization = loginClient.authorization(model.getLogin().get(), model.getPassword().get());
        if(authorization) {
            eventBus.post(Application.Command.LOGIN_SUCCESS);
        } else {
            Platform.runLater(this::showAlertNieprawidloweDaneLogowania);
        }
        Logger.info(model);

    }

    private void showAlertNieprawidloweDaneLogowania() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Nieprawidłowe dane logowania");
        alert.showAndWait();
    }

    private void onClickButtonExit(ActionEvent event) {
        eventBus.post(Application.Command.EXIT);
    }
}
