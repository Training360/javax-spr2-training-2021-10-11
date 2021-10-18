package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeConverter employeeConverter;

//    @PostConstruct
//    public void init() {
//        IntStream.range(0, 100)
//                .forEach(i -> createEmployee(new CreateEmployeeCommand("John Doe " + i)));
//
//    }

    public List<EmployeeDto> listEmployees(Optional<String> part, Pageable pageable) {
        if (!part.isPresent()) {
            return employeeRepository.findAllOrderByName(pageable);
        }
        else {
            return employeeRepository.findAllByName("%" + part.get() + "%", pageable);
        }

    }

    public EmployeeDto findEmployeeById(long id) {
        return employeeConverter.convert(employeeRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)));

    }


    public EmployeeDto createEmployee(CreateEmployeeCommand command) {

        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        log.info("User in service: " + user);


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
