package uz.pdp.applog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.applog.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
