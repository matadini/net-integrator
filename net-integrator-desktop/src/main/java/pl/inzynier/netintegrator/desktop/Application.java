package pl.inzynier.netintegrator.desktop;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.MoreExecutors;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import pl.inzynier.netintegrator.client.login.LoginClient;
import pl.inzynier.netintegrator.desktop.gui.pane.login.LoginPane;
import pl.inzynier.netintegrator.desktop.gui.pane.main.MainPane;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class Application extends javafx.application.Application {

    public enum Command {
        LOGIN_SUCCESS,
        EXIT
    }

    private final EventBus eventBus = new EventBus();
    private final ExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(4));
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
        URL resource = LoginPane.class.getResource("LoginPane.fxml");
        LoginClient loginClient = LoginClient.create("test");
        LoginPane controller = new LoginPane(eventBus, loginClient);
        controller = JavaFxUtil.loadFxml(controller, resource);

        loginStage = new Stage();
        loginStage.setOnCloseRequest(this::onCloseRequest);
        loginStage.setScene(new Scene(controller));
        loginStage.show();

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

            loginStage.close();

            URL resource = MainPane.class.getResource("MainPane.fxml");

            MainPane controller = new MainPane(eventBus, executorService);
            controller = JavaFxUtil.loadFxml(controller, resource);

            Stage stage = new Stage();
            stage.setScene(new Scene(controller));
            stage.setOnCloseRequest(this::onCloseRequest);
            stage.show();
        }
    }
}
