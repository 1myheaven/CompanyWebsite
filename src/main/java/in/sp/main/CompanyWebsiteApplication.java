package in.sp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CompanyWebsiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyWebsiteApplication.class, args);
    }
}