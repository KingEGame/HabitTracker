package kg.habittracker.project.repository;

//import jakarta.transaction.Transactional;
import kg.habittracker.project.details.UserDetail;
import kg.habittracker.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
//@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
