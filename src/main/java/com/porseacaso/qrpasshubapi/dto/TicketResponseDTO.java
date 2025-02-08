package com.porseacaso.qrpasshubapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketResponseDTO {

    private String uuid;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private Boolean isConfirmed;


}
