package kg.habittracker.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Habit")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
