package ru.ryazanova.stockstat;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class StockStatApplication implements CommandLineRunner {
    private static Logger LOG = (Logger) LoggerFactory.getLogger(StockStatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StockStatApplication.class, args);
        LOG.info("STARTING THE APPLICATION");
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("StockStat app is working ...");
    }
}
