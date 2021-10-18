package employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u join fetch u.roles where u.username = :username")
    Optional<User> findByIdWithRoles(String username);

}
