package com.example.vjezba4.repository;

import com.example.vjezba4.model.Device;
import com.example.vjezba4.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordsRepository extends JpaRepository<Records,Integer> {
    List<Records> findByYear(int year);
    List<Records> findById(int id);
}
