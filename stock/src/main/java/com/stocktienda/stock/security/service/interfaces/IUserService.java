package com.stocktienda.stock.security.service.interfaces;

import com.stocktienda.stock.security.dto.UserDto;
import com.stocktienda.stock.security.model.User;


public interface IUserService {
    
    public boolean login(UserDto user);
    
    public User register(UserDto user);
    
}
