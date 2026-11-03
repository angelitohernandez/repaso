package com.example.repaso.service;

import com.example.repaso.controller.dto.RequestCrearTicketDTO;
import com.example.repaso.controller.dto.RequestReasignarPrioridadDTO;
import com.example.repaso.models.Ticket;
import com.example.repaso.models.Usuario;
import com.example.repaso.repository.TicketRepository;
import com.example.repaso.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;


    public MyService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Ticket crearTicket (RequestCrearTicketDTO payload){
        Usuario usuario = usuarioRepository.findById(payload.getIdUsuario()).orElseThrow(
                () -> new RuntimeException("El usuario no existe"));

        if (!usuario.isActivo()){
            throw new RuntimeException("El usuari no existe");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(payload.getDescripcion());
        ticket.setEstado("Abierto");
        ticket.setPrioridad(payload.getPrioridad());
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }

    public Ticket actualizarEstadoTicket(Long idTicket){
        Ticket ticket = ticketRepository.findById(idTicket).orElseThrow(() -> new RuntimeException("El ticket no existe"));
    if (ticket.getEstado().equals("Abierto")){
        ticket.setEstado("En proseso ");
        }else if (ticket.getEstado().equals("En proseso")){
        ticket.setEstado("Cerrado");
        }else {
            throw new RuntimeException("El ticket esta cerrado");
        }
    // Actualizar ticket
        return ticketRepository.save(ticket);
    }
    public Ticket reasignarPrioridad(RequestReasignarPrioridadDTO payload){
        Ticket ticket = ticketRepository.findById(payload.getId() ).orElseThrow(() -> new RuntimeException("El ticket no existe"));
        if (ticket.getEstado().equals("Cerrado")) {
            throw new RuntimeException("El ticket esta cerrado");
        }

        ticket.setPrioridad(payload.getPrioridad());
        return ticketRepository.save(ticket);
    }

}
