package nl.ssi.bene.todoapplicationforvincent.application.core.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UserDto {

    private Long id;

    private @NonNull String  name;

}
