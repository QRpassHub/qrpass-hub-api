package com.porseacaso.qrpasshubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RolDTO {

    private Integer id;

    @NotBlank(message = "The rol name is required")
    @Size(min = 3, max = 150, message = "The rol name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "The rol name must contain only letters")
    private String rolName;

}
