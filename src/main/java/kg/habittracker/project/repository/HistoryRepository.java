package kg.habittracker.project.repository;

import kg.habittracker.project.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
