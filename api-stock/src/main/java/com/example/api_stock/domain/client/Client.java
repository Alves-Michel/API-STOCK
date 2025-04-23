package com.example.api_stock.domain.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name ="client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private String id_client;

    private String name_client;
    private String mail_client;
    private String cnp_cpf_client;
    private String phone_client;
    private String address_client;
}
