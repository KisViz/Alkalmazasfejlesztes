package hu.alkfejl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        new SpringApplication(Main.class).run(args);
        //      http://127.0.0.1:8080/potato
    }
}