package shop.iamhyunjun.tunatalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TunaTalkApplication {
    public static void main(String[] args) {
        SpringApplication.run(TunaTalkApplication.class, args);
    }
}
