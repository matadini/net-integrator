package pl.inzynier.netintegrator.desktop.gui.user;

import com.google.common.eventbus.Subscribe;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.user.UserClient;
import pl.inzynier.netintegrator.client.user.dto.UserClientException;
import pl.inzynier.netintegrator.client.user.dto.UserReadDTO;
import pl.inzynier.netintegrator.desktop.shared.ApplicationSession;


import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
class UserPaneRemoveController extends BorderPane {

    @FXML
    private ListView<UserReadDTO> listViewUsers;

    @FXML
    private Label labelId;

    @FXML
    private Label labelLogin;

    @FXML
    private Label labelInfo;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelCreateDate;

    @FXML
    private Button buttonRemove;

    private UserPaneRemoveModel model;

    private final UserClient userClient;

    @FXML
    public void initialize() {
        model = createNewModel();
        download();
        listViewUsers.getSelectionModel().selectedItemProperty().addListener(this::onClickListView);
        buttonRemove.setOnAction(this::onClickButtonRemove);
    }

    private void onClickButtonRemove(ActionEvent actionEvent) {
        try {
            String login = model.login.get();
            String loggedUser = ApplicationSession.getInstance().getLoggedUser();
            if(loggedUser.equals(login)) {
                labelInfo.setText("Nie można usunąć aktualnie zalogowanego użytkownika");
            } else {
                userClient.delete(model.id.get());
                model = createNewModel();
                labelInfo.setText("Usunięto użytkownika");
                download();
               // la
            }


        } catch (UserClientException e) {
            e.printStackTrace();
        }
    }

    private void onClickListView(ObservableValue<? extends UserReadDTO> observable, UserReadDTO oldValue, UserReadDTO newValue) {
        if (Objects.nonNull(newValue)) {
            fillModel(newValue);
        }
    }

    @Subscribe
    public void handle(UserPaneEvent event) {
        if (event.equals(UserPaneEvent.ADDED_USER)) {
            download();
        }
    }

    private UserPaneRemoveModel createNewModel() {
        UserPaneRemoveModel newModel = new UserPaneRemoveModel();
        Bindings.bindBidirectional(labelId.textProperty(), newModel.id, new NumberStringConverter());
        Bindings.bindBidirectional(labelLogin.textProperty(), newModel.login);
        Bindings.bindBidirectional(labelPassword.textProperty(), newModel.password);

        return newModel;
    }

    private void fillModel(UserReadDTO newValue) {
        model.id.set(newValue.getUserId());
        model.login.set(newValue.getLogin());
        model.password.set(newValue.getPassword());
    }

    private void download() {
        try {

            listViewUsers.getItems().clear();
            List<UserReadDTO> all = userClient.getAll();
            listViewUsers.setItems(FXCollections.observableArrayList(all));
        } catch (UserClientException e) {
            e.printStackTrace();
        }
    }


}

