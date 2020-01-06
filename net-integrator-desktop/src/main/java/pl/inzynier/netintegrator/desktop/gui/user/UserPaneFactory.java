package pl.inzynier.netintegrator.desktop.gui.user;

import com.google.common.eventbus.EventBus;
import javafx.scene.layout.BorderPane;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.client.user.UserClient;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.io.IOException;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPaneFactory {

    public static BorderPane create(final UserClient userClient, final EventBus eventBus) throws IOException {
        URL scriptFxmlUrl = UserPaneController.class.getResource("UserPane.fxml");
        UserPaneController scriptController = new UserPaneController(userClient, eventBus);
        return JavaFxUtil.loadFxml(scriptController, scriptFxmlUrl);
    }
}
