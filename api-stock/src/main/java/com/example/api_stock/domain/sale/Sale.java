package com.example.api_stock.domain.sale;

import com.example.api_stock.domain.client.Client;
import com.example.api_stock.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name ="sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private String id_sale;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client idclient;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User iduser;

    private double total_sale;
    private LocalDate date_sale;

}
