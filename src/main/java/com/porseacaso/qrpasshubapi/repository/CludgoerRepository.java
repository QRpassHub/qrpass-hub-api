package com.porseacaso.qrpasshubapi.repository;

import com.porseacaso.qrpasshubapi.model.entity.Cludgoer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CludgoerRepository extends JpaRepository<Cludgoer, Integer> {
    boolean existsByEmail(String email);
    boolean existsByDni(Integer dni);
}
