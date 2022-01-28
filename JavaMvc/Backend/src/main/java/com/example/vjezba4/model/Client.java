package com.example.vjezba4.model;

import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@EnableTransactionManagement
@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;
    private String address;
    @OneToOne
    Device device;
}
