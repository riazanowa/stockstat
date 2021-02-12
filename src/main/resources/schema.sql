DROP TABLE IF EXISTS stock_quote_company;

CREATE TABLE stock_quote_company
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    symbol VARCHAR(25) NOT NULL,
    company_name  VARCHAR(250) NOT NULL,
    primary_exchange  VARCHAR(250),
    latest_price DECIMAL NOT NULL,
    realtime_price DECIMAL NOT NULL,
    previous_volume BIGINT NOT NULL,
    volume BIGINT,
    last_trade_time BIGINT
);
