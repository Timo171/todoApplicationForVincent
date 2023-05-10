package nl.ssi.bene.todoapplicationforvincent.application.core.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    private String description;

    @Embedded
    private Status status;

    @ManyToOne
    @ToString.Exclude
    private Todo todo;

}
