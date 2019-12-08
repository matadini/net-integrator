package pl.inzynier.netintegrator.desktop.shared.event;

import lombok.AllArgsConstructor;
import lombok.Value;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;

@Value
@AllArgsConstructor
public class SelectedUrlMappingChanged implements ApplicationEvent {

    UrlMappingReadDto urlMapping;


    @Override
    public ApplicationEventSignal getType() {
        return ApplicationEventSignal.SELECTED_URL_MAPPING_CHANGED;
    }
}
