package kg.habittracker.project.repository;

import kg.habittracker.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
//@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserByUsername(String username);
}
