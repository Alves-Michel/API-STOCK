package com.example.api_stock.dto.user;


public record RegisterRequestDTO (String name,
                                  String email,
                                  String username,
                                  String password,
                                  String cpf,
                                  String phone,
                                  String birthday,
                                  String role
                                  ) {
}
