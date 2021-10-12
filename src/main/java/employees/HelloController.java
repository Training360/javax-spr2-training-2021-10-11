package employees;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(EmployeesConfig.class)
@AllArgsConstructor
public class HelloController {

    private EmployeesConfig employeesConfig;

    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot! " + employeesConfig.getWelcome();
    }
}
