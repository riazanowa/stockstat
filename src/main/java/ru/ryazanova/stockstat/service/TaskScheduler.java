package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.ryazanova.stockstat.model.Company;
import ru.ryazanova.stockstat.model.Request;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskScheduler implements Runnable {

    private final ArrayBlockingQueue<Request> requests = new ArrayBlockingQueue<>(50);

    private final IEXCloudClient iexCloudClient;

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

    @Override
    public void run() {
        Request request = null;

        try {
            request = requests.take();
            iexCloudClient.getStockQuoteInfoAboutCompanyAndSaveIntoDB(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
