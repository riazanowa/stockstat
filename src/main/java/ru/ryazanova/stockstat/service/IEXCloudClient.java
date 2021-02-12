package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ryazanova.stockstat.dto.RefDataDTO;
import ru.ryazanova.stockstat.dto.CompanyRefDataDTO;
import ru.ryazanova.stockstat.dto.StockCompanyDTO;
import ru.ryazanova.stockstat.model.Company;
import ru.ryazanova.stockstat.model.Request;
import ru.ryazanova.stockstat.repository.CompanyRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

@Component
@RequiredArgsConstructor
public class IEXCloudClient {

    @Value("${iexapis.url.ref-data}")
    private final String REF_DATA_URL;

    private static final String STOCK_QUOTE_URI= "https://cloud.iexapis.com/stable/stock/%s/quote?token=%s";

    @Value("${iexapis.token}")
    private final String iexapisToken;

    private final RestTemplate restTemplate;

    private final StockCompanyConverter converter;

    private final CompanyRepository repository;


    public List<CompanyRefDataDTO> getCompanyRefDataDTOs() {
        try {
            RefDataDTO response = restTemplate.getForObject(new URI(REF_DATA_URL), RefDataDTO.class);
            return response.getCompanyRefDataDTOS();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayBlockingQueue<Request> createRequestsForEachTradingCompany() {
        ArrayBlockingQueue<Request> requests = new ArrayBlockingQueue<>(50);

        List<CompanyRefDataDTO> companyRefDataDTOS = getCompanyRefDataDTOs();

        for (CompanyRefDataDTO company: companyRefDataDTOS) {
            String uri = String.format(STOCK_QUOTE_URI, company.getSymbol(), iexapisToken);
            try {
                requests.put(new Request(uri));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return requests;
    }

    public void getStockQuoteInfoAboutCompanyAndSaveIntoDB(Request request) {
            StockCompanyDTO response = restTemplate.getForObject(request.getURI(), StockCompanyDTO.class );
        try {
            repository.save(converter.convertToEntity(response));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
