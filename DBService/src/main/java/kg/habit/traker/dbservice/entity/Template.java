package kg.habit.traker.dbservice.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Template")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    private long aiml;
    private Timestamp frequency;
    private Timestamp startDate;
    private Timestamp endDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Measurement measurement;
}
