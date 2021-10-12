package employees;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //List<Employee> findAllByNameLikeOrderByName(String part);

    @Query("select new employees.EmployeeDto(e.id, e.name) from Employee e where e.name like :part order by e.name")
    List<EmployeeDto> findAllByName(String part, Pageable pageable);

    @Query("select new employees.EmployeeDto(e.id, e.name) from Employee e order by e.name")
    List<EmployeeDto> findAllOrderByName(Pageable pageable);

}
