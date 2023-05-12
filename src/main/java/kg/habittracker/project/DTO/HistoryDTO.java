package kg.habittracker.project.DTO;


import lombok.*;

import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {

    private Long id;

    @ManyToOne
    private HabitDTO habit;

    private Timestamp put;
}
