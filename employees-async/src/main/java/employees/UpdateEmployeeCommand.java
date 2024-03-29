package employees;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateEmployeeCommand {

    @NotBlank
    private String name;
}
