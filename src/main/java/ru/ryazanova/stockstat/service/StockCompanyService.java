package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ryazanova.stockstat.dto.CompanyRefDataDTO;
import ru.ryazanova.stockstat.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockCompanyService {

    private final IEXCloudClient iexCloudClient;

    private final StockCompanyConverter converter;

    private final CompanyRepository repository;

    public void saveStockQuoteInfoForEachCompanyIntoDB() {
        repository.save(iexCloudClient.getStockQuoteInfoAboutEachCompany());
    }

}
