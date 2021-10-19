package employees;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
@Tag( name = "Operations on employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> employees(@RequestParam Optional<String> part, @ParameterObject @PageableDefault(page = 0, size = 20) Pageable pageable) {
        return employeeService.listEmployees(part, pageable);
    }

    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping // nem idempotens
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // spring expression language kifejezést

    @Operation(summary = "creates a new employee", description = "Creates a new employee." +
            "Validates the name.")
    @ApiResponse(responseCode = "201",
            description = "Employee has been created")
    @ApiResponse(responseCode = "404",
            description = "Employee not found")
    public EmployeeDto createEmployee(@Valid @RequestBody CreateEmployeeCommand command) {
        return employeeService.createEmployee(command);
    }

    @PutMapping("/{id}") // idempotens
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @Valid @RequestBody UpdateEmployeeCommand command) {
        return employeeService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
    }

}
