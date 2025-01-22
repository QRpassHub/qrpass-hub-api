package com.porseacaso.qrpasshubapi.service.imp;

import com.porseacaso.qrpasshubapi.dto.CludgoerDTO;
import com.porseacaso.qrpasshubapi.exception.BadRequestException;
import com.porseacaso.qrpasshubapi.mapper.CludgoerMapper;
import com.porseacaso.qrpasshubapi.model.entity.Cludgoer;
import com.porseacaso.qrpasshubapi.repository.CludgoerRepository;
import com.porseacaso.qrpasshubapi.service.CludgoerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CludgoerServiceImp implements CludgoerService {

    private final CludgoerRepository cludgoerRepository;
    private final CludgoerMapper cludgoerMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CludgoerDTO> getAll() {
        List<Cludgoer> cludgoers = cludgoerRepository.findAll();
        return cludgoers.stream().map(cludgoerMapper::toDto).toList();
    }


    @Transactional(readOnly = true)
    @Override
    public Page<CludgoerDTO> paginate(Pageable pageable) {
        Page<Cludgoer> cludgoers = cludgoerRepository.findAll(pageable);
        return cludgoers.map(cludgoerMapper::toDto);
    }


    @Transactional(readOnly = true)
    @Override
    public CludgoerDTO getById(Integer id) {
        Cludgoer cludgoer = cludgoerRepository.findById(id).orElse(null);
        return cludgoerMapper.toDto(cludgoer);
    }


    @Transactional
    @Override
    public CludgoerDTO create(CludgoerDTO cludgoerDTO) {
        if (cludgoerRepository.existsByEmail(cludgoerDTO.getEmail())){
            throw new BadRequestException("Email already exists");
        }
        if (cludgoerRepository.existsByDni(cludgoerDTO.getDni())){
            throw new BadRequestException("DNI already exists");
        }
        Cludgoer cludgoer = cludgoerMapper.toEntity(cludgoerDTO);
        cludgoer = cludgoerRepository.save(cludgoer);

        return cludgoerMapper.toDto(cludgoer);
    }

    @Transactional
    @Override
    public CludgoerDTO update(Integer id, CludgoerDTO cludgoerDTO) {
        Cludgoer cludgoerToUpdate = cludgoerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cludgoer not found"));

        if (cludgoerRepository.existsByEmail(cludgoerDTO.getEmail())){
            throw new BadRequestException("Email already exists");
        }
        if (cludgoerRepository.existsByDni(cludgoerDTO.getDni())){
            throw new BadRequestException("DNI already exists");
        }
        cludgoerToUpdate.setDni(cludgoerDTO.getDni());
        cludgoerToUpdate.setEmail(cludgoerDTO.getEmail());
        cludgoerToUpdate.setName(cludgoerDTO.getName());
        cludgoerToUpdate.setPaternalLastName(cludgoerDTO.getPaternalLastName());
        cludgoerToUpdate.setMaternalLastName(cludgoerDTO.getMaternalLastName());

        cludgoerToUpdate = cludgoerRepository.save(cludgoerToUpdate);
        return cludgoerMapper.toDto(cludgoerToUpdate);
    }


    @Transactional
    @Override
    public void delete(Integer id) {
        Cludgoer cludgoerToDelete = cludgoerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cludgoer not found"));
        cludgoerRepository.delete(cludgoerToDelete);
    }
}
