package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.models.Damaged;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDamagedRepository extends JpaRepository<Damaged, Long>{
    
}
