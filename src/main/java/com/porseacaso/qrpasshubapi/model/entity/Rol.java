package com.porseacaso.qrpasshubapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rol_name", nullable = false, unique = true, columnDefinition = "Text")
    private String rolName;
}
