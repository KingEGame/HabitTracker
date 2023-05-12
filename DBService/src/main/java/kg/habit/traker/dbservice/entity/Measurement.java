package kg.habit.traker.dbservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Measurement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "Name")
    private String name;
}
