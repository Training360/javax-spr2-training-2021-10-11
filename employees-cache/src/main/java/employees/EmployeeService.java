package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
//import java.util.stream.IntStream;

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

    @Cacheable(value = "employees", condition = "#pageable.offset < 3")
    public List<EmployeeDto> listEmployees(Optional<String> part, Pageable pageable) {

        if (!part.isPresent()) {
            return employeeRepository.findAllOrderByName(pageable);
        }
        else {
            return employeeRepository.findAllByName("%" + part.get() + "%", pageable);
        }

    }

    @Cacheable("employee")
    public EmployeeDto findEmployeeById(long id) {
        return employeeConverter.convert(employeeRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)));

    }

    @CacheEvict(value = "employees", allEntries = true)
    public EmployeeDto createEmployee(CreateEmployeeCommand command) {

        log.info("Create employee");
        log.debug("Create employee with name {}", command.getName());

        Employee employee = new Employee(command.getName());
        employeeRepository.save(employee);
        return employeeConverter.convert(employee);

    }

    @Transactional
    @Caching(evict = {
//        @CacheEvict(value = "employee", key = "#id"),
        @CacheEvict(value = "employees", allEntries = true)})

    @CachePut(value = "employee", key = "#id")
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employeeToModify = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeToModify.setName(command.getName());
        return employeeConverter.convert(employeeToModify);

    }

    @Caching(evict = {
        @CacheEvict("employee"),
        @CacheEvict(value = "employees", allEntries = true)})
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);

    }
}
