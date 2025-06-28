package com.cibertec.edu.Facturacion_service.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ReservationDTO {
    private Integer idReserva;
    private String numeroHabitacion;
    private String tipoHabitacion;
    private String fechaLlegada;
    private String fechaSalida;
    private Integer cantidadPersonas;
    private String estado;
    private BigDecimal total;
    private String fechaCreacion;
}

