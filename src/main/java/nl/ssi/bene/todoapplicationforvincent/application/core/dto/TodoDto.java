package nl.ssi.bene.todoapplicationforvincent.application.core.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class TodoDto {

    private Long id;

    private @NonNull Long createdBy;

    private Instant createdAt;

    private @NonNull List<TaskDto> actions;

    private Boolean finished;

    private Instant finishedAt;

}
