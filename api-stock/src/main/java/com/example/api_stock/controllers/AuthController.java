package com.example.api_stock.controllers;

import com.example.api_stock.domain.user.User;
import com.example.api_stock.dto.user.LoginRequestDTO;
import com.example.api_stock.dto.user.RegisterRequestDTO;
import com.example.api_stock.dto.user.ResponseDTO;
import com.example.api_stock.infra.security.TokenService;
import com.example.api_stock.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByUsername(body.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.repository.findByUsername(body.name());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(body.username());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setCpf(body.cpf());
            newUser.setName(body.name());
            newUser.setPhone(body.phone());
            newUser.setBirthday(LocalDate.parse(body.birthday(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            newUser.setRole(body.role());


            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

}