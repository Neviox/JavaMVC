package com.example.vjezba4.repository;

import com.example.vjezba4.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Integer> {
    List<Device> findByName(String name);

}
