package com.porseacaso.qrpasshubapi.repository;

import com.porseacaso.qrpasshubapi.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Optional<Ticket> getTicketByUuid(String uuid);

}
