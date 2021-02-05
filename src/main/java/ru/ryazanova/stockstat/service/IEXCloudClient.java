package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ryazanova.stockstat.dto.CompaniesDTO;
import ru.ryazanova.stockstat.dto.CompanyDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IEXCloudClient {

    @Value("$stock.stat.url")
    private String url;

    private final RestTemplate restTemplate;

    public List<CompanyDTO> getCompaniesDTO() {
        try {
            CompaniesDTO response = restTemplate.getForObject(new URI(url), CompaniesDTO.class);
            return response.getCompanyDTOS();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }
}
