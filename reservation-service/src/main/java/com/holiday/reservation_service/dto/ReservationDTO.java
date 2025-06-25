package com.holiday.reservation_service.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ReservationDTO(Integer idUsuario,
                             Integer idHabitacion,
                             Date fechaLlegada,
                             Date fechaSalida,
                             Integer cantidadPersonas,
                             BigDecimal total) {
} // para la creacion de reserva
