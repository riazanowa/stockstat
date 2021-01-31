package ru.ryazanova.stockstat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ryazanova.stockstat.model.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
}
