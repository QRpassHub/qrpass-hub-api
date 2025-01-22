package com.porseacaso.qrpasshubapi.mapper;

import com.porseacaso.qrpasshubapi.dto.CludgoerDTO;
import com.porseacaso.qrpasshubapi.model.entity.Cludgoer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CludgoerMapper {

    private final ModelMapper modelMapper;

    public CludgoerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CludgoerDTO toDto (Cludgoer cludgoer){
        return modelMapper.map(cludgoer, CludgoerDTO.class);
    }

    public Cludgoer toEntity(CludgoerDTO cludgoerDTO){
        return modelMapper.map(cludgoerDTO, Cludgoer.class);
    }
}
