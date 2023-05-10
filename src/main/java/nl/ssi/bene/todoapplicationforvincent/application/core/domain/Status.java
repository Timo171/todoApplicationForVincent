package nl.ssi.bene.todoapplicationforvincent.application.core.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Basic
    private Boolean finished;

    @Basic
    private Instant finishedTime;

}
