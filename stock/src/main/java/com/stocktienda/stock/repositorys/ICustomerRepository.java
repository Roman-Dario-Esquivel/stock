package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.models.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {


    public Optional<Customer> findByDni(@Param("dni") Long dni);

}
