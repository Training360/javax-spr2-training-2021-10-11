package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@EnableConfigurationProperties(EmployeesConfig.class)
@AllArgsConstructor
@Slf4j
public class HelloController {

    private EmployeesConfig employeesConfig;

    @GetMapping("/")
    public String hello(@AuthenticationPrincipal User user) {
        log.info("User: " + user.getUsername() + " " + user.getRoles());
        return "Hello Spring Boot! " + employeesConfig.getWelcome();
    }
}
