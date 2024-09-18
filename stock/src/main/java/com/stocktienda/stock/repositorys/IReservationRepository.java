package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.ModelsAuxiliary.ReservationData;
import com.stocktienda.stock.models.Reservation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

        @Query("SELECT new com.stocktienda.stock.ModelsAuxiliary.ReservationData(p.id, p.price, p.balance, p.deposit, pr.description) " +
           "FROM Reservation p JOIN p.product pr WHERE p.active = false")
    public List<ReservationData> findAllWithoutActive();
    
    public Optional<Reservation> findByIdReservation(@Param("id") Long id);

}
