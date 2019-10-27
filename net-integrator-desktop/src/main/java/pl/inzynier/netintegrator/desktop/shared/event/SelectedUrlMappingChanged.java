package pl.inzynier.netintegrator.desktop.shared.event;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class SelectedUrlMappingChanged implements ApplicationEvent {

    Long urlMappingId;


    @Override
    public ApplicationEventSignal getType() {
        return ApplicationEventSignal.SELECTED_URL_MAPPING_CHANGED;
    }
}
