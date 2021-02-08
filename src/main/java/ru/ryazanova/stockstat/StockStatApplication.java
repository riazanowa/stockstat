package ru.ryazanova.stockstat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockStatApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(StockStatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("StockStat app is working ...");

    }
}
