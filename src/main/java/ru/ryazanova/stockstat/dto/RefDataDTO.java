package ru.ryazanova.stockstat.dto;

import lombok.Data;

import java.util.List;

@Data
public class RefDataDTO {
    private List<CompanyRefDataDTO> companyRefDataDTOS;
}
