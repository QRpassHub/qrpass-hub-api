package com.porseacaso.qrpasshubapi.service.imp;

import com.google.zxing.WriterException;
import com.porseacaso.qrpasshubapi.component.QRCodeGeneratorComponent;
import com.porseacaso.qrpasshubapi.dto.TicketDTO;
import com.porseacaso.qrpasshubapi.dto.TicketResponseDTO;
import com.porseacaso.qrpasshubapi.exception.ResourceNotFoundException;
import com.porseacaso.qrpasshubapi.mapper.TicketMapper;
import com.porseacaso.qrpasshubapi.model.entity.Ticket;
import com.porseacaso.qrpasshubapi.repository.TicketRepository;
import com.porseacaso.qrpasshubapi.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImp implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final QRCodeGeneratorComponent qrCodeGenerator;


    @Transactional(readOnly = true)
    @Override
    public List<TicketResponseDTO> getAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(ticketMapper::toResponseDto).toList();
    }


    @Transactional(readOnly = true)
    @Override
    public Page<TicketResponseDTO> paginate(Pageable pageable) {
        Page<Ticket> tickets = ticketRepository.findAll(pageable);
        return tickets.map(ticketMapper::toResponseDto);
    }


    @Transactional(readOnly = true)
    @Override
    public TicketResponseDTO getById(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
        return ticketMapper.toResponseDto(ticket);
    }


    @Transactional(readOnly = true)
    @Override
    public TicketResponseDTO getByUuid(String uuid) {
        Ticket ticket = ticketRepository.getTicketByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with UUID: " + uuid));
        return ticketMapper.toResponseDto(ticket);
    }


    @Transactional
    @Override
    public TicketResponseDTO create(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket.setUuid(java.util.UUID.randomUUID().toString());
        ticket.setCreationDate(LocalDateTime.now());
        ticket = ticketRepository.save(ticket);

        return ticketMapper.toResponseDto(ticket);
    }


    @Transactional
    @Override
    public byte[] generateQRCode(String uuid) {
        Ticket ticket = ticketRepository.getTicketByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with UUID: " + uuid));
        try {
            return qrCodeGenerator.generateQRCode(ticket.getUuid(), 200, 200);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Error generating QR Code", e);
        }
    }


    @Transactional
    @Override
    public TicketResponseDTO update(Integer id, TicketDTO ticketDTO) {
        Ticket ticketToUpdate = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));

        ticketToUpdate.setExpirationDate(ticketDTO.getExpirationDate());
        ticketToUpdate.setIsConfirmed(ticketDTO.getIsConfirmed());
        ticketToUpdate = ticketRepository.save(ticketToUpdate);

        return ticketMapper.toResponseDto(ticketToUpdate);
    }


    @Transactional
    @Override
    public TicketResponseDTO confirm(String uuid) {
        Ticket ticket = ticketRepository.getTicketByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with UUID: " + uuid));
        ticket.setIsConfirmed(true);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toResponseDto(ticket);
    }


    @Transactional
    @Override
    public void delete(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
        ticketRepository.delete(ticket);
    }
}
