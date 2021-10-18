package employees;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderTest {

    @Test
    void testEncode() {
        String encoded = new BCryptPasswordEncoder().encode("johndoe");
        System.out.println(encoded);
    }
}
