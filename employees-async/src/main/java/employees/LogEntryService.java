package employees;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LogEntryService {

    private LogEntryRepository logEntryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(String name) {
        LogEntry logEntry = new LogEntry();
        logEntry.setMessage(name);
        logEntryRepository.save(logEntry);
    }
}
