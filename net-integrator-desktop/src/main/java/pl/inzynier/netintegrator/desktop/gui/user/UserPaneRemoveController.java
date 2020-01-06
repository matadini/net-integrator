package pl.inzynier.netintegrator.desktop.gui.user;

import com.google.common.eventbus.EventBus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.user.UserClient;
import pl.inzynier.netintegrator.client.user.dto.UserReadDTO;


@RequiredArgsConstructor
class UserPaneRemoveController extends BorderPane {

    @FXML
    private ListView<UserReadDTO> listViewUsers;

    @FXML
    private Label labelId;

    @FXML
    private Label labelLogin;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelCreateDate;

    @FXML
    private Button buttonRemove;


    private final UserClient userClient;

    @FXML
    public void initialize() {

    }
}

