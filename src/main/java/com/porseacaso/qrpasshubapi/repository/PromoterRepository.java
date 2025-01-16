package com.porseacaso.qrpasshubapi.repository;

import com.porseacaso.qrpasshubapi.model.entity.Promoter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoterRepository extends JpaRepository<Promoter, Integer> {
    boolean existsByEmail(String email);
    boolean existByDni(Integer dni);
}
