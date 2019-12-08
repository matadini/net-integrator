package pl.inzynier.netintegrator.user;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll() {
        String sql = "select u from User u order by u.userId";
        return entityManager.createQuery(sql, User.class).getResultList();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public User findByLoginAndPasswordMD5(String login, String passwordMD5) {
        try {
            String sql = "select u from User u where u.login = :login and u.passwordMD5 = :passwordMD5";
            TypedQuery<User> query = entityManager.createQuery(sql, User.class);
            query.setParameter("login", login);
            query.setParameter("passwordMD5", passwordMD5);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
