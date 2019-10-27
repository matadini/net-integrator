package pl.inzynier.netintegrator.desktop.gui.script;

import javafx.beans.property.*;
import pl.inzynier.netintegrator.http.util.RequestMethod;

class ScriptPaneModel {
    LongProperty urlMappingId = new SimpleLongProperty();
    StringProperty targetEndpointURL = new SimpleStringProperty();
    StringProperty targetEndpointServer = new SimpleStringProperty();
    ObjectProperty<RequestMethod> targetEndpointMethod = new SimpleObjectProperty<>();

    StringProperty publishEndpointURL = new SimpleStringProperty();
    ObjectProperty<RequestMethod> publishEndpointMethod = new SimpleObjectProperty<>();
}
