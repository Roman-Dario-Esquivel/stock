package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long>{
    
}
