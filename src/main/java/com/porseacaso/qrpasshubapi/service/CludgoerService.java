package com.porseacaso.qrpasshubapi.service;

import com.porseacaso.qrpasshubapi.dto.CludgoerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CludgoerService {
    List<CludgoerDTO> getAll();
    Page<CludgoerDTO> paginate(Pageable pageable);
    CludgoerDTO getById(Integer id);
    CludgoerDTO create(CludgoerDTO cludgoerDTO);
    CludgoerDTO update(Integer id, CludgoerDTO cludgoerDTO);
    void delete(Integer id);
}
