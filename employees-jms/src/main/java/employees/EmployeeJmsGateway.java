package employees;

import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeJmsGateway {

    private JmsTemplate jmsTemplate;

    public void send(String text) {
        EmployeeEvent employeeEvent = new EmployeeEvent(text);
        jmsTemplate.convertAndSend("employeesQueue", employeeEvent);
    }

    @JmsListener(destination = "employeesQueue")
    public void processMessage(EmployeeEvent event) {
        System.out.println("Event has come: " + event.getText());
    }
}
