package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.ModelsAuxiliary.CustomerData;
import com.stocktienda.stock.models.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT new com.stocktienda.stock.ModelsAuxiliary.CustomerData(p.dni, p.name, p.numberMobile, "
            + "new com.stocktienda.stock.ModelsAuxiliary.ReservationData(r.id, r.price, r.balance, r.deposit, pr.description)) "
            + "FROM Customer p JOIN p.reservations r JOIN r.product pr "
            + "WHERE p.dni = :id AND r.active = false")
    public Optional<CustomerData> findByDniCustomerData(@Param("id") Long id);
    
    public Optional<Customer> findByDniCustomer(@Param("dni") Long dni);
    

}
