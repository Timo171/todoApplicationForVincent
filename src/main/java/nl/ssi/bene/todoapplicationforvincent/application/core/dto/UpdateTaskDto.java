package nl.ssi.bene.todoapplicationforvincent.application.core.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UpdateTaskDto {

    private String action;

    private Boolean finished;

    private Instant finishedAt;

}
