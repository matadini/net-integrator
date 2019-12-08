package pl.inzynier.netintegrator.desktop;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.inzynier.netintegrator.client.user.UserClient;
import pl.inzynier.netintegrator.desktop.gui.login.LoginPane;
import pl.inzynier.netintegrator.desktop.gui.main.MainPane;
import pl.inzynier.netintegrator.desktop.shared.CommonStrings;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEvent;
import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;
import pl.inzynier.netintegrator.desktop.shared.event.SignalOnly;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;


public class Application extends javafx.application.Application {

    private final EventBus eventBus = new EventBus();
    private final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(4));
    private static Application instance;
    private Stage loginStage;
    private static boolean loginOff = false;
    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<String>(Arrays.asList(args));
        if (arrayList.contains("user-off")) {
            loginOff = true;
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // zarejestruj instancje aplikacji na eventbusa
        instance = this;
        eventBus.register(instance);

        if(loginOff) {
            SignalOnly signalOnly = SignalOnly.of(ApplicationEventSignal.LOGIN_SUCCESS);
            eventBus.post(signalOnly);
        } else {

            URL resource = LoginPane.class.getResource("LoginPane.fxml");
            UserClient userClient = UserClient.create("test");
            LoginPane controller = new LoginPane(eventBus, userClient);
            controller = JavaFxUtil.loadFxml(controller, resource);

            loginStage = new Stage();
            loginStage.setTitle(CommonStrings.APP_NAME);
            loginStage.setOnCloseRequest(this::onCloseRequest);
            loginStage.setScene(new Scene(controller));
            loginStage.show();
        }
    }

    private void onCloseRequest(WindowEvent event) {
        SignalOnly signalOnly = SignalOnly.of(ApplicationEventSignal.EXIT);
        eventBus.post(signalOnly);
    }

    @Subscribe
    public void handle(ApplicationEvent command) throws Exception {

        if (ApplicationEventSignal.EXIT.equals(command.getType())) {
            org.pmw.tinylog.Logger.info("Zamknij aplikacje");

            eventBus.unregister(instance);
            Platform.exit();
        }

        if (ApplicationEventSignal.LOGIN_SUCCESS.equals(command.getType())) {
            org.pmw.tinylog.Logger.info("Odpal aplikacje");

            Optional.ofNullable(loginStage).ifPresent(Stage::close);


            URL resource = MainPane.class.getResource("MainPane.fxml");

            MainPane controller = new MainPane(eventBus, executorService);
            controller = JavaFxUtil.loadFxml(controller, resource);

            Stage stage = new Stage();
            stage.setWidth(1024);
            stage.setHeight(768);
            stage.setScene(new Scene(controller));
            stage.setOnCloseRequest(this::onCloseRequest);
            stage.show();
        }
    }
}
