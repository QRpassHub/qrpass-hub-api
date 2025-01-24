package com.porseacaso.qrpasshubapi.controller;

import com.porseacaso.qrpasshubapi.dto.TicketDTO;
import com.porseacaso.qrpasshubapi.dto.TicketResponseDTO;
import com.porseacaso.qrpasshubapi.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;


    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
        List<TicketResponseDTO> tickets = ticketService.getAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }


    @GetMapping("/paginate")
    public ResponseEntity<Page<TicketResponseDTO>> getAllTicketsPaginate(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<TicketResponseDTO> tickets = ticketService.paginate(pageable);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Integer id) {
        TicketResponseDTO ticket = ticketService.getById(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<TicketResponseDTO> getTicketByUuid(@PathVariable String uuid) {
        TicketResponseDTO ticket = ticketService.getByUuid(uuid);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        TicketResponseDTO newTicket = ticketService.create(ticketDTO);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }


    @GetMapping("/{uuid}/qr")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable String uuid) {
        byte[] qrCode = ticketService.generateQRCode(uuid);
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=\"qrcode.png\"")
                .contentType(org.springframework.http.MediaType.IMAGE_PNG)
                .body(qrCode);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(
            @PathVariable Integer id, @Valid @RequestBody TicketDTO ticketDTO) {
        TicketResponseDTO updatedTicket = ticketService.update(id, ticketDTO);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }


    @PutMapping("/confirm/{uuid}")
    public ResponseEntity<TicketResponseDTO> confirmTicket(@PathVariable String uuid) {
        TicketResponseDTO confirmedTicket = ticketService.confirm(uuid);
        return new ResponseEntity<>(confirmedTicket, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Integer id) {
        ticketService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
