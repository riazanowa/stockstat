package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ryazanova.stockstat.dto.CompanyRefDataDTO;
import ru.ryazanova.stockstat.dto.StockCompanyDTO;
import ru.ryazanova.stockstat.model.Company;
import ru.ryazanova.stockstat.model.Request;
import ru.ryazanova.stockstat.repository.CompanyRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockCompanyService implements Runnable  {

    @Qualifier("fixedThreadPool")
    private final ExecutorService executorService;

    private final IEXCloudClient iexCloudClient;

    private final StockCompanyConverter converter;

    private final CompanyRepository repository;

    ArrayBlockingQueue<Request> requests = createQueueOfRequestsForEachCompany();

    public List<Company> getAllCompanies() {
       List<Company> companies = repository.findAll().stream()
               .sorted(Comparator.comparing(Company::getVolume).thenComparing(Company::getCompanyName))
               .collect(Collectors.toList());
       return companies;
    }

    public void updateInfoAboutEachCompanyInDB(Company company) {
            repository.save(company);
    }

    public ArrayBlockingQueue<Request> createQueueOfRequestsForEachCompany() {
        List<Request> requests = iexCloudClient.createListOfRequestsForEachTradingCompany();
        return new ArrayBlockingQueue<>(requests.size(), true, requests);
    }

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


    //Кажется, бред
    public void executeWithResult() {
        executorService.submit(this::run);
    }


}
