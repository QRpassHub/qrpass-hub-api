package com.porseacaso.qrpasshubapi.service;

import com.porseacaso.qrpasshubapi.dto.TicketDTO;
import com.porseacaso.qrpasshubapi.dto.TicketResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {

    List<TicketResponseDTO> getAll();
    Page<TicketResponseDTO> paginate(Pageable pageable);
    TicketResponseDTO getById(Integer id);
    TicketResponseDTO getByUuid(String uuid);
    TicketResponseDTO create(TicketDTO ticketDTO);
    byte[] generateQRCode(String uuid);
    TicketResponseDTO update(Integer id, TicketDTO ticketDTO);
    TicketResponseDTO confirm(String uuid);
    void delete(Integer id);

}
