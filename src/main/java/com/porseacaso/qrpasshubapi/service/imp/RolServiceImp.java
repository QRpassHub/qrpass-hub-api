package com.porseacaso.qrpasshubapi.service.imp;

import com.porseacaso.qrpasshubapi.dto.RolDTO;
import com.porseacaso.qrpasshubapi.exception.BadRequestException;
import com.porseacaso.qrpasshubapi.mapper.RolMapper;
import com.porseacaso.qrpasshubapi.model.entity.Rol;
import com.porseacaso.qrpasshubapi.repository.RolRepository;
import com.porseacaso.qrpasshubapi.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RolServiceImp implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Transactional(readOnly = true)
    @Override
    public List<RolDTO> getAll() {
        List<Rol> roles = rolRepository.findAll();
        return roles.stream().map(rolMapper::toDto).toList();
    }


    @Transactional(readOnly = true)
    @Override
    public Page<RolDTO> paginate(Pageable pageable) {
        Page<Rol> roles = rolRepository.findAll(pageable);
        return roles.map(rolMapper::toDto);
    }


    @Transactional
    @Override
    public RolDTO getById(Integer id) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new BadRequestException("Rol not found"));
        return rolMapper.toDto(rol);
    }


    @Transactional
    @Override
    public RolDTO create(RolDTO rolDTO) {
        if(rolRepository.existsByRolName(rolDTO.getRolName())){
            throw new BadRequestException("Rol already exists");
        }

        Rol rol = rolMapper.toEntity(rolDTO);
        rol = rolRepository.save(rol);

        return rolMapper.toDto(rol);
    }


    @Transactional
    @Override
    public RolDTO update(Integer id, RolDTO rolDTO) {
        Rol rolToUpdate = rolRepository.findById(id).orElseThrow(() -> new BadRequestException("Rol not found"));

        if (rolRepository.existsByRolName(rolDTO.getRolName())){
            throw new BadRequestException("Rol already exists");
        }

        rolToUpdate.setRolName(rolDTO.getRolName());

        rolToUpdate = rolRepository.save(rolToUpdate);
        return rolMapper.toDto(rolToUpdate);

    }


    @Transactional
    @Override
    public void delete(Integer id) {

        Rol rolToDelete = rolRepository.findById(id).orElseThrow(() -> new BadRequestException("Rol not found"));
        rolRepository.delete(rolToDelete);

    }
}
