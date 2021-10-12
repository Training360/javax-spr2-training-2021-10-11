package employees;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "empapp")
@Data
public class EmployeesConfig {

    private String welcome;
}
