package com.porseacaso.qrpasshubapi.repository;

import com.porseacaso.qrpasshubapi.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    boolean existsByRolName(String rolName);

}
