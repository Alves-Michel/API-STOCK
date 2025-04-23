package com.example.api_stock.domain.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "dados_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private String id_user;

    @Column(name = "name_user")
    private String name;

    @Column(name ="login_user")
    private String username;

    @Column(name = "mail_user")
    private String email;

    @Column(name = "password_user")
    private String password;

    @Column(name ="date_birth_user")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(name ="cpf_user")
    @CPF(message = "CPF inválido!")
    private String cpf;

    @Column(name ="type_user")
    private String role;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role);
    }

    @Column(name = "phone_user")
    private String phone;


}
