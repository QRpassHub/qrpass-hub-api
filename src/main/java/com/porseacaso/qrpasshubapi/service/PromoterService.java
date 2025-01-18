package com.porseacaso.qrpasshubapi.service;

import com.porseacaso.qrpasshubapi.dto.PromoterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromoterService {
    List<PromoterDTO> getAll();
    Page<PromoterDTO> paginate(Pageable pageable);
    PromoterDTO getById(Integer id);
    PromoterDTO create(PromoterDTO promoterDTO);
    PromoterDTO update(Integer id, PromoterDTO promoterDTO);
    void delete(Integer id);
}

