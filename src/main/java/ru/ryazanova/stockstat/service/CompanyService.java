package ru.ryazanova.stockstat.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ryazanova.stockstat.model.Company;
import ru.ryazanova.stockstat.dto.CompanyDTO;
import ru.ryazanova.stockstat.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final IEXCloudClient iexCloudClient;

    private final CompanyRepository repository;

    public List<Company> findAll() {
        return iexCloudClient.getCompaniesDTO().stream()
                .map(this::convertToCompany)
                .collect(Collectors.toUnmodifiableList());
    }

    private Company convertToCompany(@NonNull CompanyDTO companyDTO) {
        return new Company(
                companyDTO.getSymbol(),
                companyDTO.getName(),
                companyDTO.getDate(),
                companyDTO.getType(),
                companyDTO.getIexId(),
                companyDTO.getRegion(),
                companyDTO.getCurrency(),
                companyDTO.isEnabled(),
                companyDTO.getFigi(),
                companyDTO.getCik());
    }

}
