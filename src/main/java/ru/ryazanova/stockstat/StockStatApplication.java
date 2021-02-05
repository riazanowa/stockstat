package ru.ryazanova.stockstat;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ryazanova.stockstat.model.Company;
import ru.ryazanova.stockstat.service.CompanyService;

import java.util.List;
import java.util.logging.Logger;

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
