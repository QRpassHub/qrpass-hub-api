package com.porseacaso.qrpasshubapi.mapper;

import com.porseacaso.qrpasshubapi.dto.TicketDTO;
import com.porseacaso.qrpasshubapi.dto.TicketResponseDTO;
import com.porseacaso.qrpasshubapi.model.entity.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    private final ModelMapper modelMapper;

    public TicketMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TicketResponseDTO toResponseDto (Ticket ticket){
        return modelMapper.map(ticket, TicketResponseDTO.class);
    }

    public Ticket toEntity (TicketDTO ticketDTO){
        return modelMapper.map(ticketDTO, Ticket.class);
    }
}
