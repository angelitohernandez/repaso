package com.example.repaso.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestCrearTicketDTO {

    private long idUsuario;
    @NotBlank(message = "La descripcion no debe estar blanca♠")
    private String descripcion;
    private String prioridad;


}
