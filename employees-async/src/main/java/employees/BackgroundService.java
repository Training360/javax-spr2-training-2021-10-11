package employees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BackgroundService {

    @Async
    public void doJob() {
        try {
            Thread.sleep(5000);
            log.info("Background job end");
        } catch (InterruptedException ie) {
            log.error("Interrupted", ie);
        }
    }

    @Scheduled(fixedRate = 5000) // 5 másodpercenként
    public void logCount() {
        log.info("Running scheduled job");
    }
}
