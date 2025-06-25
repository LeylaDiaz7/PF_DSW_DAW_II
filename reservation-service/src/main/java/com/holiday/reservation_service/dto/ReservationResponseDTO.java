package com.holiday.reservation_service.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public record ReservationResponseDTO (Integer idReserva,
                                      String numeroHabitacion,
                                      String tipoHabitacion,
                                      @JsonFormat(pattern = "dd/MM/yyyy") // Formato día/mes/año
                                      Date fechaLlegada,
                                      @JsonFormat(pattern = "dd/MM/yyyy") // Formato día/mes/año
                                      Date fechaSalida,
                                      Integer cantidadPersonas,
                                      String estado,
                                      BigDecimal total,
                                      Date fechaCreacion) {
} // respuesta de la api
