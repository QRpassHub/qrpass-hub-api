package com.porseacaso.qrpasshubapi.mapper;

import com.porseacaso.qrpasshubapi.dto.RolDTO;
import com.porseacaso.qrpasshubapi.model.entity.Rol;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    private final ModelMapper modelMapper;

    public RolMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RolDTO toDto (Rol rol){
        return modelMapper.map(rol, RolDTO.class);
    }

    public Rol toEntity(RolDTO rolDTO){
        return modelMapper.map(rolDTO, Rol.class);
    }
}
