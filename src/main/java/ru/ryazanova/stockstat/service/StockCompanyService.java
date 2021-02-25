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
public class StockCompanyService {

    @Qualifier("fixedThreadPool")
    private final ExecutorService executorService;

    private final CompanyRepository repository;

    public List<Company> getAllCompanies() {
       List<Company> companies = repository.findAll().stream()
               .sorted(Comparator.comparing(Company::getVolume).thenComparing(Company::getCompanyName))
               .collect(Collectors.toList());
       return companies;
    }

    public void executeWithResult() {
        while (true) {
            executorService.execute(new StockCompanyTask());
        }

    }


}
