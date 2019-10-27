package pl.inzynier.netintegrator.desktop.shared.event;


import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Map;

/**
 * Nie uzywaj enuma do bezposredniego wysylania na eventBusa,
 * uzywaj klas aby utrzymac spojnosc
 */
public enum ApplicationEventSignal {
    URL_MAPPING_CREATE,
    URL_MAPPING_REMOVE,
    SELECTED_URL_MAPPING_CHANGED,
    SCRIPT_CREATE,
    LOGIN_SUCCESS,
    EXIT
}
