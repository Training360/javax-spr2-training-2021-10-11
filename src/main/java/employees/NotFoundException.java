package employees;

//import org.zalando.problem.AbstractThrowableProblem;
//import org.zalando.problem.Status;

import java.net.URI;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
//extends AbstractThrowableProblem {
//
//    public NotFoundException(String message) {
//        super(URI.create("not-found"), "Not found", Status.NOT_FOUND, message);
//    }
//
//}
