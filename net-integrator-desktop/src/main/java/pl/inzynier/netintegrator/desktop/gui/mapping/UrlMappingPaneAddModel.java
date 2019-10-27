package pl.inzynier.netintegrator.desktop.gui.mapping;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;
import pl.inzynier.netintegrator.http.util.RequestMethod;

@ToString
class UrlMappingPaneAddModel {

    StringProperty targetEndpointURL = new SimpleStringProperty();
    StringProperty targetEndpointServer = new SimpleStringProperty();
    ObjectProperty<RequestMethod> targetEndpointMethod = new SimpleObjectProperty<>();

    StringProperty publishEndpointURL = new SimpleStringProperty();
    ObjectProperty<RequestMethod> publishEndpointMethod = new SimpleObjectProperty<>();

}
