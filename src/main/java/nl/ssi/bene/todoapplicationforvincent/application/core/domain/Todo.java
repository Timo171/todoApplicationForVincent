package nl.ssi.bene.todoapplicationforvincent.application.core.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    private User user;

    @Embedded
    private Status status;

    @Basic
    private final Instant creationTime = Instant.now();

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Task> tasks;


}
