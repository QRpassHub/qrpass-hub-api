package com.porseacaso.qrpasshubapi.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "Text")
    private String uuid;

    @Column(name = "creation_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;

    @Column(name = "expiration_date", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime expirationDate;

    @Column(name = "is_confirmed", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean isConfirmed;


    @ManyToOne
    @JoinColumn(name = "cludgoer_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_ticket_cludgoer"))
    private Cludgoer cludgoer;

    @ManyToOne
    @JoinColumn(name = "promoter_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_ticket_promoter"))
    private Promoter promoter;



}
