package pl.inzynier.netintegrator.desktop.gui.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
class LoginPaneModel {
    private final StringProperty login = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
}
