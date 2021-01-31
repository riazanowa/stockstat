package ru.ryazanova.stockstat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyDTO {
    private String symbol;
    private String name;
    private Date date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    private boolean isEnabled;
    private String figi;
    private String cik;
}
