package ru.ryazanova.stockstat.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.ryazanova.stockstat.dto.StockCompanyDTO;
import ru.ryazanova.stockstat.model.Company;

import java.text.ParseException;

@RequiredArgsConstructor
@Component
public class StockCompanyConverter {

    private final ModelMapper modelMapper;

    private StockCompanyDTO convertToDto(Company company) {
        StockCompanyDTO stockCompanyDTO = modelMapper.map(company, StockCompanyDTO.class);
        return stockCompanyDTO;
    }

    public Company convertToEntity(StockCompanyDTO stockCompanyDTO) throws ParseException {
        Company company = modelMapper.map(stockCompanyDTO, Company.class);
        return company;
    }


}
