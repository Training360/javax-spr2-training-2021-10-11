package employees;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeConverter employeeConverter;

    public List<EmployeeDto> listEmployees(Optional<String> part) {
        if (part.isEmpty()) {
            return employeeRepository.findAllOrderByName();
        }
        else {
            return employeeRepository.findAllByName("%" + part.get() + "%");
        }

    }

    public EmployeeDto findEmployeeById(long id) {
        return employeeConverter.convert(employeeRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)));

    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {

        Employee employee = new Employee(command.getName());
        employeeRepository.save(employee);
        return employeeConverter.convert(employee);

    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employeeToModify = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeToModify.setName(command.getName());
        return employeeConverter.convert(employeeToModify);

    }

    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);

    }
}
