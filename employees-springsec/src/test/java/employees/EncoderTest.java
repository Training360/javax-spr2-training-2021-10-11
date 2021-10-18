package employees;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderTest {

    @Test
    void testEncode() {
        System.out.println(new BCryptPasswordEncoder().encode("johndoe"));
        System.out.println(new BCryptPasswordEncoder().encode("jackdoe"));
    }
}
