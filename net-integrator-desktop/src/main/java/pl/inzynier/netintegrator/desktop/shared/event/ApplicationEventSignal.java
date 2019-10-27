package pl.inzynier.netintegrator.desktop.shared.event;


import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Map;

public enum ApplicationEventSignal {
    URL_MAPPING_CREATE,
    URL_MAPPING_REMOVE,
    SCRIPT_SELECTED_CHANGED,
    SCRIPT_CREATE,
}
