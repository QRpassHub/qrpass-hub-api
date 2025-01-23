package com.porseacaso.qrpasshubapi.service;

import com.porseacaso.qrpasshubapi.dto.RolDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RolService {
    List<RolDTO> getAll();
    Page<RolDTO> paginate(Pageable pageable);
    RolDTO getById(Integer id);
    RolDTO create(RolDTO rolDTO);
    RolDTO update(Integer id, RolDTO rolDTO);
    void delete(Integer id);

}
