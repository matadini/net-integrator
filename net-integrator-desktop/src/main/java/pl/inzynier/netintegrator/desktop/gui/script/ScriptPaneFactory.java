package pl.inzynier.netintegrator.desktop.gui.script;

import com.google.common.eventbus.EventBus;
import javafx.scene.layout.BorderPane;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.client.script.ScriptClient;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.io.IOException;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScriptPaneFactory {

    public static BorderPane create(UrlMappingClient managmentClient, ScriptClient scriptClient, EventBus eventBus) throws IOException {
        URL scriptFxmlUrl = ScriptPane.class.getResource("ScriptPane.fxml");
        ScriptPane scriptController = new ScriptPane(managmentClient, scriptClient, eventBus);
        return JavaFxUtil.loadFxml(scriptController, scriptFxmlUrl);
    }
}
