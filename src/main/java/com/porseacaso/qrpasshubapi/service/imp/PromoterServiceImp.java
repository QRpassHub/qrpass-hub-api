package com.porseacaso.qrpasshubapi.service.imp;

import com.porseacaso.qrpasshubapi.dto.PromoterDTO;
import com.porseacaso.qrpasshubapi.exception.BadRequestException;
import com.porseacaso.qrpasshubapi.exception.ResourceNotFoundException;
import com.porseacaso.qrpasshubapi.mapper.PromoterMapper;
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
    private final PromoterMapper promoterMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PromoterDTO> getAll() {
        List<Promoter> promoters = promoterRepository.findAll();
        return promoters.stream().map(promoterMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PromoterDTO> paginate(Pageable pageable) {
        Page<Promoter> promoters = promoterRepository.findAll(pageable);
        return promoters.map(promoterMapper::toDto);
    }

    @Transactional
    @Override
    public PromoterDTO getById(Integer id) {
        Promoter promoter = promoterRepository.findById(id).orElse(null);
        return promoterMapper.toDto(promoter);
    }

    @Transactional
    @Override
    public PromoterDTO create(PromoterDTO promoterDTO) {
        if(promoterRepository.existsByEmail(promoterDTO.getEmail())){
            throw new BadRequestException("Email already exists");
        }
        if (promoterRepository.existsByDni(promoterDTO.getDni())){
            throw new BadRequestException("DNI already exists");
        }
        Promoter promoter = promoterMapper.toEntity(promoterDTO);
        promoter = promoterRepository.save(promoter);

        return promoterMapper.toDto(promoter);
    }

    @Transactional
    @Override
    public PromoterDTO update(Integer id, PromoterDTO promoterDTO) {
        Promoter promoterToUpdate = promoterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promoter not found"));

        if(promoterRepository.existsByEmail(promoterDTO.getEmail())){
            throw new BadRequestException("Email already exists");
        }
        if (promoterRepository.existsByDni(promoterDTO.getDni())){
            throw new BadRequestException("DNI already exists");
        }
        promoterToUpdate.setDni(promoterDTO.getDni());
        promoterToUpdate.setEmail(promoterDTO.getEmail());
        promoterToUpdate.setName(promoterDTO.getName());
        promoterToUpdate.setPaternalLastName(promoterDTO.getPaternalLastName());
        promoterToUpdate.setMaternalLastName(promoterDTO.getMaternalLastName());

        promoterToUpdate = promoterRepository.save(promoterToUpdate);
        return promoterMapper.toDto(promoterToUpdate);


    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Promoter promoterToDelete = promoterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promoter not found"));

        promoterRepository.delete(promoterToDelete);

    }
}
