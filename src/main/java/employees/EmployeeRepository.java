package employees;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeRepository {

    private AtomicLong idGenerator = new AtomicLong();

    private List<Employee> employees = new ArrayList<>(
       Arrays.asList(new Employee(idGenerator.incrementAndGet(), "John Doe"), new Employee(idGenerator.incrementAndGet(), "Jane Doe"))
    );


    public List<Employee> findAll() {
        return employees;
    }

    public Optional<Employee> findById(long id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findAny();

    }

    public void save(Employee employee) {
        employee.setId(idGenerator.incrementAndGet());
        employees.add(employee);
    }

    public void delete(Employee employee) {
        employees.remove(employee);
    }
}
