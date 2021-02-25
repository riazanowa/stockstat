package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.ryazanova.stockstat.model.Company;
import ru.ryazanova.stockstat.model.Request;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskScheduler{

    private final StockCompanyService stockCompanyService;


    @Scheduled(fixedRate = 5000)
    public void reportStockQuoteInfo() {
       List<Company> companies = stockCompanyService.getAllCompanies();

        System.out.println("The top 5 highest value stocks: ");
        for (int i = 0; i < 5; i++) {
            System.out.println(companies.get(i));
        }

        System.out.println();

        System.out.println("The most recent 5 companies that have the greatest change percent in stock value:");
        List<Company> recentCompanies =companies.stream().sorted(Comparator.comparing(o1 -> (o1.getIexRealtimePrice().subtract(o1.getLatestPrice()))))
                .collect(Collectors.toList());

        for (int i = 0; i < 5; i++) {
            System.out.println(recentCompanies.get(i));
        }

    }
}
