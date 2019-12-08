package pl.inzynier.netintegrator.user.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserServiceFactory {
    public static UserService create(EntityManagerFactoryProvider provider) {
        return null;
    }
}
