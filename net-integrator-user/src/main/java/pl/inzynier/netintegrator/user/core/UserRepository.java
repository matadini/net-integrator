package pl.inzynier.netintegrator.user.core;

import pl.inzynier.netintegrator.db.util.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByLoginAndPassword(String login, String passwordMD5);

    User findByLogin(String login);
}

