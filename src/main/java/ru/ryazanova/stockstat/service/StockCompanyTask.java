package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ryazanova.stockstat.dto.StockCompanyDTO;
import ru.ryazanova.stockstat.model.Company;
import ru.ryazanova.stockstat.model.Request;
import ru.ryazanova.stockstat.repository.CompanyRepository;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

@RequiredArgsConstructor
@Component
public class StockCompanyTask implements Runnable{

    private final IEXCloudClient iexCloudClient;

    private final StockCompanyConverter converter;

    private final CompanyRepository repository;

    private ArrayBlockingQueue<Request> requests = createQueueOfRequestsForEachCompany();

    @Override
    public void run() {
        Request request;
        try {
            request = requests.take();
            StockCompanyDTO response = iexCloudClient.getStockQuoteInfoAboutCompany(request);
            updateInfoAboutEachCompanyInDB(converter.convertToEntity(response));

        } catch (InterruptedException e) {
            System.out.println("Exception with blocking queue");
        } catch (ParseException e) {
            System.out.println("Parse Exception of companyDTO into company");
        }
    }

    public void updateInfoAboutEachCompanyInDB(Company company) {
        repository.save(company);
    }

    public ArrayBlockingQueue<Request> createQueueOfRequestsForEachCompany() {
        List<Request> requests = iexCloudClient.createListOfRequestsForEachTradingCompany();
        return new ArrayBlockingQueue<>(requests.size(), true, requests);
    }
}
