package ru.ryazanova.stockstat.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompaniesDTO {
    private List<CompanyDTO> companyDTOS;
}
