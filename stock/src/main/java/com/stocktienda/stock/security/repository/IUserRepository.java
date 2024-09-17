package com.stocktienda.stock.security.repository;

import com.stocktienda.stock.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User, Long>{
    
    public boolean existsByUsername(String name);
    
    User findByUsername(String username);
    
}
