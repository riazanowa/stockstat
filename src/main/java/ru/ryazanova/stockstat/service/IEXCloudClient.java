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
import java.util.ArrayList;
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


    public List<CompanyRefDataDTO> getCompanyRefDataDTOs() {
        try {
            RefDataDTO response = restTemplate.getForObject(new URI(REF_DATA_URL), RefDataDTO.class);
            return response.getCompanyRefDataDTOS();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Request> createListOfRequestsForEachTradingCompany() {
        List<Request> requests = new ArrayList<>();

        List<CompanyRefDataDTO> companyRefDataDTOS = getCompanyRefDataDTOs();

        for (CompanyRefDataDTO company: companyRefDataDTOS) {
            String uri = String.format(STOCK_QUOTE_URI, company.getSymbol(), iexapisToken);
            requests.add(new Request(uri));
        }
        return requests;
    }

    public StockCompanyDTO getStockQuoteInfoAboutCompany(Request request) {
            StockCompanyDTO response = restTemplate.getForObject(request.getURI(), StockCompanyDTO.class );
            return response;
    }


}
