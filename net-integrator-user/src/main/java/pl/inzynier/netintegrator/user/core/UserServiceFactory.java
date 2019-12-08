package pl.inzynier.netintegrator.user.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceFactory {
    public static UserService create(EntityManagerFactoryProvider provider) {
        EntityManagerFactory entityManagerFactoryH2 = provider.get();
        EntityManager entityManager = entityManagerFactoryH2.createEntityManager();
        UserRepository userRepository = new UserRepositoryImpl(entityManager);
        return new UserServiceImpl(userRepository);
    }
}
