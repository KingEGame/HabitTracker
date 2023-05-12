package kg.habit.traker.dbservice.entity;

//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "History")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Habit habit;

    private Timestamp put;
}
