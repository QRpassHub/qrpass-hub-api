package com.porseacaso.qrpasshubapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PromoterDTO {

    private Integer id;

    @Min(value = 10000000, message = "DNI must have 8 digits")
    @Max(value = 99999999, message = "DNI must have 8 digits")
    private Integer dni;

    @NotBlank(message = "Name is required")
    @Email(message = "Email is required")
    @Size(min = 3, message = "Name must be between 3 and 100 characters")
    private String email;;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String paternalLastName;

    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String maternalLastName;


}
