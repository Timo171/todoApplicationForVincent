package nl.ssi.bene.todoapplicationforvincent.application.core.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.Instant;

@Data
@Builder
public class UpdateTodoTaskDto {

    private @NonNull Long id;

    private String action;

    private Boolean finished;

    private Instant finishedAt;

}
