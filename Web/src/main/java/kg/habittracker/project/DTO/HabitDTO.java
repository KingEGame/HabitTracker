package kg.habittracker.project.DTO;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitDTO {
    private Long id;

    private String name;

    private String description;
    private long aiml;
    private Timestamp frequency;
    private Timestamp startDate;
    private Timestamp endDate;

    private UserDTO user;

    private MeasurmentDTO measurement;
}
