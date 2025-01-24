package com.porseacaso.qrpasshubapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDTO {

    private Integer id;

    @NotNull(message = "Expiration date cannot be null")
    private LocalDateTime expirationDate;

    @NotNull(message = "Is confirmed cannot be null")
    private Boolean isConfirmed;

    @NotNull(message = "Cludgoer id cannot be null")
    @Min(value = 1, message = "Cludgoer id must be greater than 0")
    private Integer cludgoerId;

    @NotNull(message = "Promoter id cannot be null")
    @Min(value = 1, message = "Promoter id must be greater than 0")
    private Integer promoterId;
}
