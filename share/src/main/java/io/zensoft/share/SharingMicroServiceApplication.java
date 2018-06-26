package io.zensoft.share;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SharingMicroServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SharingMicroServiceApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }

}
