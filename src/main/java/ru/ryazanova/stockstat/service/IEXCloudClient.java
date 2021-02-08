package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ryazanova.stockstat.dto.RefDataDTO;
import ru.ryazanova.stockstat.dto.CompanyRefDataDTO;
import ru.ryazanova.stockstat.dto.StockCompanyDTO;
import ru.ryazanova.stockstat.model.Company;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

@Component
@RequiredArgsConstructor
public class IEXCloudClient {

    private ArrayBlockingQueue<String> requests = new ArrayBlockingQueue<>(50);

    @Value("${iexapis.url.ref-data}")
    private final String REF_DATA_URL;

    private static final String STOCK_QUOTE_URI= "https://cloud.iexapis.com/stable/stock/%s/quote?token=%s";

    @Value("${iexapis.token}")
    private final String iexapisToken;

    private final RestTemplate restTemplate;

    private final StockCompanyConverter converter;


    public List<CompanyRefDataDTO> getCompanyRefDataDTOs() {
        try {
            RefDataDTO response = restTemplate.getForObject(new URI(REF_DATA_URL), RefDataDTO.class);
            return response.getCompanyRefDataDTOS();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void createRequestForEachTradingCompany() {
        List<CompanyRefDataDTO> companyRefDataDTOS = getCompanyRefDataDTOs();
        for (CompanyRefDataDTO company: companyRefDataDTOS) {
            String uri = String.format(STOCK_QUOTE_URI, company.getSymbol(), iexapisToken);
            try {
                requests.put(uri);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //Очень плохая  недоделанная версия метода
    public Company getStockQuoteInfoAboutEachCompany() {
            String URI;
            try {
                URI = requests.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StockCompanyDTO response = restTemplate.getForObject(URI, StockCompanyDTO.class );
        try {
           return converter.convertToEntity(response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
