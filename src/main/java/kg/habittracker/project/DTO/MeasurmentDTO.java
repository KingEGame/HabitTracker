package kg.habittracker.project.DTO;


import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurmentDTO {

    private Long id;

    private String name;
}
