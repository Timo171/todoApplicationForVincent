package nl.ssi.bene.todoapplicationforvincent.application.core.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UpdateTodoDto {

    private Long createdBy;

    private List<UpdateTodoTaskDto> actions;

    private Boolean finished;

    private Instant finishedAt;

}
