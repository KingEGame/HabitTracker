package kg.habittracker.project.entity;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Email(message = "Email address has invalid format: ${validatedValue}",
            regexp = "^[a-zA-Z0-9_.+-]+@gmail.com")
    @Column(name = "email", length = 120)
    private String email;

    private String password;
}
