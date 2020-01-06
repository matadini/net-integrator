package pl.inzynier.netintegrator.desktop.gui.mapping;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;
import pl.inzynier.netintegrator.http.util.RequestMethod;

@ToString
class UrlMappingPaneAddModel {
    StringProperty targetURL =
            new SimpleStringProperty();

    StringProperty targetServer =
            new SimpleStringProperty();

    ObjectProperty<RequestMethod> targetMethod =
            new SimpleObjectProperty<>();

    StringProperty publishURL =
            new SimpleStringProperty();

    ObjectProperty<RequestMethod> publishMethod =
            new SimpleObjectProperty<>();
}
