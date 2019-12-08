package pl.inzynier.netintegrator.user;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.db.util.JpaRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
}

