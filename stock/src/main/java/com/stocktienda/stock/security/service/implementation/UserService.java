package com.stocktienda.stock.security.service.implementation;

import com.stocktienda.stock.security.dto.UserDto;
import com.stocktienda.stock.security.model.User;
import com.stocktienda.stock.security.repository.IUserRepository;
import com.stocktienda.stock.security.service.interfaces.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean login(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        return user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword());
    }

    public User register(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        return userRepository.save(user);
    }

}
