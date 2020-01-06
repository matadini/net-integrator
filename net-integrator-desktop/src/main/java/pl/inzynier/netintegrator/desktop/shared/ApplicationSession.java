package pl.inzynier.netintegrator.desktop.shared;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationSession {

    private static ApplicationSession instance;

    public static ApplicationSession getInstance() {
        if(instance == null) {
            instance = new ApplicationSession();
        }
        return instance;
    }

    String loggedUser;


}
