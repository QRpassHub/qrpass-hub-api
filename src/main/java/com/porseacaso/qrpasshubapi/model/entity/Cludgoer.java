package com.porseacaso.qrpasshubapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="cludgoers")
public class Cludgoer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "email", nullable = false, columnDefinition = "Text")
    private String email;

    @Column(name= "dni", nullable = false, length = 8)
    private Integer dni;

    @Column(name= "name", nullable = false, columnDefinition = "Text")
    private String name;

    @Column(name = "paternal_last_name", nullable = false, columnDefinition = "Text")
    private String paternalLastName;

    @Column(name = "maternal_last_name", nullable = false, columnDefinition = "Text")
    private String maternalLastName;


}
