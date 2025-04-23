package com.example.api_stock.repositories;

import com.example.api_stock.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <User, String>{


    Optional<User> findByUsername(String loginUser);
    Optional<User> findById(String id_user);


}
