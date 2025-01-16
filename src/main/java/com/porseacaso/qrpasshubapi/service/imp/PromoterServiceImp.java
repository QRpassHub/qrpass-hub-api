package com.porseacaso.qrpasshubapi.service.imp;

import com.porseacaso.qrpasshubapi.model.entity.Promoter;
import com.porseacaso.qrpasshubapi.repository.PromoterRepository;
import com.porseacaso.qrpasshubapi.service.PromoterService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PromoterServiceImp implements PromoterService {

    private final PromoterRepository promoterRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Promoter> getAll() {
        return promoterRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Promoter> paginate(Pageable pageable) {
        return promoterRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Promoter getById(Integer id) {
        return promoterRepository.findById(id).orElseThrow(() -> new RuntimeException("Promoter not found"));
    }

    @Transactional
    @Override
    public Promoter create(Promoter promoter) {
        if(promoterRepository.existsByEmail(promoter.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        if (promoterRepository.existsByDni(promoter.getDni())){
            throw new RuntimeException("DNI already exists");
        }
        return promoterRepository.save(promoter);
    }

    @Transactional
    @Override
    public Promoter update(Integer id, Promoter promoter) {
        Promoter promoterToUpdate = getById(id);

        if (promoterToUpdate == null){
            throw new RuntimeException("Promoter not found");
        }
        if(promoterRepository.existsByEmail(promoter.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        if (promoterRepository.existsByDni(promoter.getDni())){
            throw new RuntimeException("DNI already exists");
        }
        promoterToUpdate.setDni(promoter.getDni());
        promoterToUpdate.setEmail(promoter.getEmail());
        promoterToUpdate.setName(promoter.getName());
        promoterToUpdate.setPaternalLastName(promoter.getPaternalLastName());
        promoterToUpdate.setMaternalLastName(promoter.getMaternalLastName());
        return promoterRepository.save(promoterToUpdate);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Promoter promoterToDelete = getById(id);
        if (promoterToDelete == null){
            throw new RuntimeException("Promoter not found");
        }
        promoterRepository.delete(promoterToDelete);

    }
}
