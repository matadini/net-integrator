package pl.inzynier.netintegrator.desktop.shared.event;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class SelectedUrlMappingChanged implements ApplicationEvent {
    Long script;
    ApplicationEventSignal signal;

    @Override
    public ApplicationEventSignal getType() {
        return ApplicationEventSignal.SCRIPT_SELECTED_CHANGED;
    }
}
