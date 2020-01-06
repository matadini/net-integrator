package pl.inzynier.netintegrator.desktop.gui.user;

import com.google.common.eventbus.EventBus;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.user.UserClient;

import pl.inzynier.netintegrator.desktop.shared.CommonUtil;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.net.URL;


@RequiredArgsConstructor
class UserPaneController extends BorderPane {

    @FXML
    private TabPane tabPane;

    private final UserClient userClient;

    private final EventBus eventBus;

    private UserPaneAddController userPaneAddController;
    private UserPaneRemoveController userPaneRemoveController; //Controller;

    @FXML
    public void initialize() {

        try {
            // dodawanie
            userPaneAddController = new UserPaneAddController();
            String pathAdd = "UserPaneAdd.fxml";
            URL resourceAdd = UserPaneAddController.class.getResource(pathAdd);
            userPaneAddController = JavaFxUtil.loadFxml(userPaneAddController, resourceAdd);

            Tab tabAdd = JavaFxUtil.createNoClosableTab(CommonUtil.standardFormat("Add"));
            tabAdd.setContent(userPaneAddController);

            // usuwanie
            userPaneRemoveController = new UserPaneRemoveController();
            String pathRemove = "UserPaneRemove.fxml";
            URL resourceRemove = UserPaneRemoveController.class.getResource(pathRemove);
            userPaneRemoveController = JavaFxUtil.loadFxml(userPaneRemoveController, resourceRemove);

            Tab tabRemove = JavaFxUtil.createNoClosableTab(CommonUtil.standardFormat("Remove"));
            tabRemove.setContent(userPaneRemoveController);

            // dodaj tabsy do widoku
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.add(tabAdd);
            tabs.add(tabRemove);
          //  tabs.create(tabEdit);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

