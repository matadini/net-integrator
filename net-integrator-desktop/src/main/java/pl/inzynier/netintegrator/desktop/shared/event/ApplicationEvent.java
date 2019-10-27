package pl.inzynier.netintegrator.desktop.shared.event;

import pl.inzynier.netintegrator.desktop.shared.event.ApplicationEventSignal;

public interface ApplicationEvent {
    ApplicationEventSignal getType();
}
