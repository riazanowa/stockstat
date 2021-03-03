package ru.ryazanova.stockstat.model;

import lombok.Data;

@Data
public class Request {
    String URI;

    public Request(String URI) {
        this.URI = URI;
    }
}
