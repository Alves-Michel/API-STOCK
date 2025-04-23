package com.example.api_stock.service;

import com.example.api_stock.dto.user.UserDTO;
import com.example.api_stock.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {


    @Autowired
    private final UserRepository userRepository;

    public List<UserDTO> findAllUsers() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getName(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getCpf(),
                        user.getPhone(),
                        user.getRole(),
                        user.getBirthday() != null ? user.getBirthday().format(formatter) : null
                ))
                .collect(Collectors.toList());
    }


    public void deletedById(String userId) {
        try {

            var userExists = userRepository.existsById(userId);

            if (userExists) {
                userRepository.deleteById(userId);
            } else {
                throw new RuntimeException("User with id " + userId + " does not exist");
            }

        }catch (NumberFormatException e){
            throw new RuntimeException("ID of user invalid " + userId);
        }
    }
}
