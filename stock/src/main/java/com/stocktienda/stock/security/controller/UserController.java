package com.stocktienda.stock.security.controller;

import com.stocktienda.stock.security.dto.UserDto;
import com.stocktienda.stock.security.model.User;
import com.stocktienda.stock.security.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Usuario", description = "Operaciones relacionadas con Usuario")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @Operation(summary = "Metodo de loguin", description = "Metodo de loguin se manda usuario y contraseña ")
    public boolean login(@RequestBody UserDto user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    @Operation(summary = "Metodo de registro", description = "Se manda un Usuario y su contraseña")
    public User register(@RequestBody UserDto user) {
        return userService.register(user);
    }

}
