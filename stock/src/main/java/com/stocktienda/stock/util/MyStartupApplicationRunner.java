package com.stocktienda.stock.util;

import com.stocktienda.stock.security.dto.UserDto;
import com.stocktienda.stock.security.repository.IUserRepository;
import com.stocktienda.stock.security.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyStartupApplicationRunner implements CommandLineRunner {
    
    @Autowired
    IUserRepository userRepository;
    
    @Autowired 
    UserService userService;
    
    @Override
    public void run(String... args) throws Exception {
        // Tu lógica aquí
        String User = "administrador";
   
        if(!userRepository.existsByUsername(User)){
            
            UserDto userDto = new UserDto();
            userDto.setUsername(User);
            String password ="RocioDeMiel2524";
            userDto.setPassword(password);
            userService.register(userDto);
            System.out.println("Se genero el usuario.");
        }
        
        
    }
}
