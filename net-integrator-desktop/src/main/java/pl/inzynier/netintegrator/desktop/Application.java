package pl.inzynier.netintegrator.desktop;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import pl.inzynier.netintegrator.desktop.gui.main.MainPane;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.net.URL;
import java.util.Optional;
import java.util.concurrent.Executors;


public class Application extends javafx.application.Application {

    public enum Command {
        LOGIN_SUCCESS,
        EXIT
    }

    private final EventBus eventBus = new EventBus();
    private final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(4));
    private static Application instance;
    private Stage loginStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // zarejestruj instancje aplikacji na eventbusa
        instance = this;
        eventBus.register(instance);

        // pokaz ekran logowania
//        URL resource = LoginPane.class.getResource("LoginPane.fxml");
//        LoginClient loginClient = LoginClient.create("test");
//        LoginPane controller = new LoginPane(eventBus, loginClient);
//        controller = JavaFxUtil.loadFxml(controller, resource);
//
//        loginStage = new Stage();
//        loginStage.setOnCloseRequest(this::onCloseRequest);
//        loginStage.setScene(new Scene(controller));
//        loginStage.show();

        eventBus.post(Command.LOGIN_SUCCESS);

    }

    private void onCloseRequest(WindowEvent event) {
        eventBus.post(Command.EXIT);
    }

    @Subscribe
    public void handle(Command command) throws Exception {
        if (Command.EXIT.equals(command)) {
            org.pmw.tinylog.Logger.info("Zamknij aplikacje");

            eventBus.unregister(instance);
            Platform.exit();
        }
        if (Command.LOGIN_SUCCESS.equals(command)) {
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
