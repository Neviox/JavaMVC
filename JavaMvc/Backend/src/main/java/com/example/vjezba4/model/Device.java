package com.example.vjezba4.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.List;

@EnableTransactionManagement
@Table(name="devices")
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_device")
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Records> recordings;


}
