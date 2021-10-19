package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
//import java.util.stream.IntStream;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeConverter employeeConverter;

    private BackgroundService backgroundService;

    private LogEntryService logEntryService;

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
        log.info(backgroundService.toString());
        backgroundService.doJob();
        Employee employee = new Employee(command.getName());
        employeeRepository.save(employee);

        logEntryService.save("Employee has been created");

        throw new IllegalStateException("Tuti rollback lesz");

//        return employeeConverter.convert(employee);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        // begin
        Employee employeeToModify = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeToModify.setName(command.getName());
        return employeeConverter.convert(employeeToModify);
        // commit
    }

    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);

    }
}
