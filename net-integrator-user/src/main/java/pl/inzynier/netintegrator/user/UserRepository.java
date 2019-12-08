package pl.inzynier.netintegrator.user;

import pl.inzynier.netintegrator.db.util.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByLoginAndPasswordMD5(String login, String passwordMD5);
}

