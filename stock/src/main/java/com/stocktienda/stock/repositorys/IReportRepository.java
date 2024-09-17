package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReportRepository extends JpaRepository<Report, Long>{
    
    
    
}
