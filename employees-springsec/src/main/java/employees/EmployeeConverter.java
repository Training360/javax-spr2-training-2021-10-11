package employees;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeConverter {

    List<EmployeeDto> convert(List<Employee> employees);

    EmployeeDto convert(Employee employee);

}
