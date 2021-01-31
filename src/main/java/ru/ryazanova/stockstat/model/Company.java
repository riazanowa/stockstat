package ru.ryazanova.stockstat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table( name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

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

    protected Company() {}

    public Company(String symbol, String name, Date date, String type,
                   String iexId, String region, String currency,
                   boolean isEnabled, String figi, String cik) {
        this.symbol = symbol;
        this.name = name;
        this.date = date;
        this.type = type;
        this.iexId = iexId;
        this.region = region;
        this.currency = currency;
        this.isEnabled = isEnabled;
        this.figi = figi;
        this.cik = cik;
    }
}
