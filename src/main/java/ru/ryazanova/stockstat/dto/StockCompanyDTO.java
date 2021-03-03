package ru.ryazanova.stockstat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockCompanyDTO {

    private String symbol;
    private String companyName;
    private String primaryExchange;
    private BigDecimal latestPrice;
    private Long previousVolume;
    private Long volume;
    private Long lastTradeTime;
}
