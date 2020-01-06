package pl.inzynier.netintegrator.desktop.gui.user;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class UserPaneAddModel {
    StringProperty login = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    StringProperty confirmPassword = new SimpleStringProperty();
}
