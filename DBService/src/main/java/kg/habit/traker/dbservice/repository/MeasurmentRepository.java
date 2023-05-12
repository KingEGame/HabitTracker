package kg.habit.traker.dbservice.repository;

import kg.habit.traker.dbservice.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurmentRepository extends JpaRepository<Measurement, Long> {
}
