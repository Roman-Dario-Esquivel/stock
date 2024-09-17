package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long>{
    
}
