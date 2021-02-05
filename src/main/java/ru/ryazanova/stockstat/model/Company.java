package ru.ryazanova.stockstat.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table( name = "company")
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "type")
    private String type;

    @Column(name = "iexId")
    private String iexId;

    @Column(name = "region")
    private String region;

    @Column(name = "currency")
    private String currency;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "figi")
    private String figi;

    @Column(name = "cik")
    private String cik;

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
