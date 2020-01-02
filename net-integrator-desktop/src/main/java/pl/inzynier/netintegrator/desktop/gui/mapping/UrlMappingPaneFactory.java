package pl.inzynier.netintegrator.desktop.gui.mapping;

import com.google.common.eventbus.EventBus;
import javafx.scene.layout.BorderPane;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.UrlMappingClient;
import pl.inzynier.netintegrator.desktop.shared.JavaFxUtil;

import java.io.IOException;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlMappingPaneFactory {

    public static BorderPane create(UrlMappingClient managmentClient, EventBus eventBus) throws IOException {
        URL mappingFxmlUrl = UrlMappingPaneController.class.getResource("UrlMappingPaneController.fxml");
        UrlMappingPaneAddModelToUrlMappingWriteDto mapper = new UrlMappingPaneAddModelToUrlMappingWriteDto();
        UrlMappingPaneController mappingController = new UrlMappingPaneController(managmentClient, eventBus, mapper);
        mappingController = JavaFxUtil.loadFxml(mappingController, mappingFxmlUrl);
        return mappingController;
    }
}
