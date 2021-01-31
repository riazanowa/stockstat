package ru.ryazanova.stockstat.service;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ryazanova.stockstat.dto.CompaniesDTO;
import ru.ryazanova.stockstat.dto.CompanyDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.List;

@Component
public class IEXCloudClient {
    private RestTemplate restTemplate = new RestTemplate();

    public List<CompanyDTO> getCompaniesDTO() {
        String url = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";

        try {
            CompaniesDTO response = restTemplate.getForObject(new URI(url), CompaniesDTO.class);
            return response.getCompanyDTOS();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }
}
