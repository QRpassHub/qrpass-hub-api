package com.porseacaso.qrpasshubapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CludgoerDTO {

    private Integer id;

    @Email(message = "Email is required")
    private String email;

    @Min(value = 10000000, message = "DNI must have 8 digits")
    @Max(value = 99999999, message = "DNI must have 8 digits")
    private Integer dni;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String paternalLastName;

    @NotBlank
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String maternalLastName;
}
