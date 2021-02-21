package ru.ryazanova.stockstat;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;

@SpringBootApplication
@AllArgsConstructor
public class StockStatApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StockStatApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("StockStat app is working ...");

    }


}
