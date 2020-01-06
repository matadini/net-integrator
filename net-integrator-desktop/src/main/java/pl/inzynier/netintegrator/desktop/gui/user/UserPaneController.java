package pl.inzynier.netintegrator.desktop.gui.user;

import com.google.common.eventbus.EventBus;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.user.UserClient;

import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.net.URL;


@RequiredArgsConstructor
class UserPaneController extends BorderPane {

    @FXML
    private TabPane tabPane;

    private final UserClient userClient;

    private final EventBus eventBus;

    private UserPaneAddController userPaneAddController;

    @FXML
    public void initialize() {

        try {
            // dodawanie
            userPaneAddController = new UserPaneAddController();
            String path = "UserPaneAdd.fxml";
            URL resource = UserPaneAddController.class.getResource(path);
            userPaneAddController = JavaFxUtil.loadFxml(userPaneAddController, resource);

            Tab tabAdd = JavaFxUtil.createNoClosableTab("       Dodawanie       ");
            tabAdd.setContent(userPaneAddController);

            // dodaj tabsy do widoku
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.add(tabAdd);
          //  tabs.add(tabEdit);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

