package pl.inzynier.netintegrator.desktop.gui.script;

import javafx.util.StringConverter;
import pl.inzynier.netintegrator.http.util.RequestMethod;

import java.util.Objects;

class RequestMethodStringConverter extends StringConverter<RequestMethod> {
    @Override
    public String toString(RequestMethod object) {
        return Objects.nonNull(object) ? object.toString() : null;
    }

    @Override
    public RequestMethod fromString(String string) {
        return Objects.nonNull(string) ? RequestMethod.valueOf(string) : null;
    }
}
