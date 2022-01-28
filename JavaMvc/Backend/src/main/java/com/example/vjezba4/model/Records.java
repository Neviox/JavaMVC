package com.example.vjezba4.model;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Table
@Entity
@Data
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int year;
    private int january;
    private int february;
    private int march;
    private int april;
    private int may;
    private int june;
    private int july;
    private int august;
    private int september;
    private int october;
    private int november;
    private int december;

}
