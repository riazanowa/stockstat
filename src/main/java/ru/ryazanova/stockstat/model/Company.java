package ru.ryazanova.stockstat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table( name = "stock_quote_company")
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "realtime_price")
    private BigDecimal iexRealtimePrice;

    @Column(name = "primary_exchange")
    private String primaryExchange;

    @Column(name = "latest_price")
    private BigDecimal latestPrice;

    @Column(name = "previous_volume")
    private Long previousVolume;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "last_trade_time")
    private Long lastTradeTime;

}
