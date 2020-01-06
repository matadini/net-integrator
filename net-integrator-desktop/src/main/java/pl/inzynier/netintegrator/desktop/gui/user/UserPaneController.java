package pl.inzynier.netintegrator.desktop.gui.user;

import com.google.common.eventbus.EventBus;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.user.UserClient;


@RequiredArgsConstructor
class UserPaneController extends BorderPane {

    @FXML
    private TabPane tabPane;

    private final UserClient userClient;

    private final EventBus eventBus;

    @FXML
    public void initialize() {

    }
}
