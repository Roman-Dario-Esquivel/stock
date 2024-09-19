package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.models.Reservation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

        @Query("SELECT p FROM Reservation p WHERE p.active = true")
    public List<Reservation> findAllWithoutActive();
    
    public Optional<Reservation> findById(@Param("id") Long id);

}
