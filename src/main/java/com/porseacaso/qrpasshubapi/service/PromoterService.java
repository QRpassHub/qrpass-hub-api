package com.porseacaso.qrpasshubapi.service;

import com.porseacaso.qrpasshubapi.model.entity.Promoter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromoterService {
    List<Promoter> getAll();
    Page<Promoter> paginate(Pageable pageable);
    Promoter getById(Integer id);
    Promoter create(Promoter promoter);
    Promoter update(Integer id, Promoter promoter);
    void delete(Integer id);
}
