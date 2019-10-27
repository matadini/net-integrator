package pl.inzynier.netintegrator.desktop.shared.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignalOnly implements ApplicationEvent  {

    ApplicationEventSignal signal;

    @Override
    public ApplicationEventSignal getType() {
        return signal;
    }

    public static SignalOnly of(ApplicationEventSignal signal) {
        return new SignalOnly(signal);
    }
}
