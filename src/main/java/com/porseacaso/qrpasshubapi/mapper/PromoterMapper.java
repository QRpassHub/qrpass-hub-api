package com.porseacaso.qrpasshubapi.mapper;

import com.porseacaso.qrpasshubapi.dto.PromoterDTO;
import com.porseacaso.qrpasshubapi.model.entity.Promoter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PromoterMapper {

    private final ModelMapper modelMapper;

    public PromoterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PromoterDTO toDto (Promoter promoter){
        return modelMapper.map(promoter, PromoterDTO.class);
    }

    public Promoter toEntity (PromoterDTO promoterDTO){
        return modelMapper.map(promoterDTO, Promoter.class);
    }
}
