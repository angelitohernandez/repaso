package com.example.repaso.controller;

import com.example.repaso.controller.dto.RequestCrearTicketDTO;
import com.example.repaso.controller.dto.RequestReasignarPrioridadDTO;
import com.example.repaso.models.Ticket;
import com.example.repaso.service.MyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myservices")
@CrossOrigin("*")

public class MyController {
    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @PostMapping("/crear-ticket")
    public ResponseEntity<Ticket> crearTicket(@RequestBody @Valid RequestCrearTicketDTO payload){
        return ResponseEntity.status(201).body(myService.crearTicket(payload));
    }

    @PatchMapping("/actualizar-estado/{id}")
    public ResponseEntity<Ticket> actualizarTicket
            (@RequestParam Long id){
        return ResponseEntity.status(200).body(myService.actualizarEstadoTicket(id));
    }
    @PatchMapping("/reasignar-prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad
            (@RequestBody RequestReasignarPrioridadDTO payload){
        return ResponseEntity
                .status(200)
                .body(
                        myService.reasignarPrioridad(payload)
                );
    }
}
