package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ryazanova.stockstat.dto.CompanyRefDataDTO;
import ru.ryazanova.stockstat.model.Company;
import ru.ryazanova.stockstat.repository.CompanyRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockCompanyService {

    private final IEXCloudClient iexCloudClient;

    private final StockCompanyConverter converter;

    private final CompanyRepository repository;

    public List<Company> getAllCompanies() {
       List<Company> companies = repository.findAll().stream()
               .sorted(Comparator.comparing(Company::getVolume).thenComparing(Company::getCompanyName))
               .collect(Collectors.toList());
       return companies;
    }


}
