package com.example.api_stock.controllers;

import com.example.api_stock.dto.user.UserDTO;
import com.example.api_stock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final  UserService userService;

    @GetMapping
    public ResponseEntity<String> getUser(){

        return ResponseEntity.ok("sucesso!");
    }

@GetMapping("/list")
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        var users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    //nao esta funcionando ainda
    @DeleteMapping("/delete/{id_user}")
    public ResponseEntity deleteUserById(@PathVariable("id_user") String userId) {
        userService.deletedById(userId);
        return ResponseEntity.ok().body("User has been deleted");
    }
}
