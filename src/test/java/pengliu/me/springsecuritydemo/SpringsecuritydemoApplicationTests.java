package pengliu.me.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringsecuritydemoApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String encoded = passwordEncoder.encode("1234");
        System.out.println(encoded);
        Boolean ret = passwordEncoder.matches("1234", "$2a$10$1lnPKooF8.PpKer1F1sdeOYgwIo0onyowSdzkD49wzTngE1UOMyAe");
        System.out.println(ret);
    }

}
