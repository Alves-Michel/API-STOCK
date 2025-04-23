package com.example.api_stock.dto.user;



public record UserDTO (String name,
                       String email,
                       String username,
                       String cpf,
                       String phone,
                       String role,
                       String birthday) {
}
