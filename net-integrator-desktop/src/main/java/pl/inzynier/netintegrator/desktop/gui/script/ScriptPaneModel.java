package pl.inzynier.netintegrator.desktop.gui.script;

import javafx.beans.property.*;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;

class ScriptPaneModel {
    LongProperty scriptId = new SimpleLongProperty();
    StringProperty content = new SimpleStringProperty();
    ObjectProperty<ScriptType> type = new SimpleObjectProperty<>();
}
