package com.holiday.reservation_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Room room;

    @Column(name = "fecha_llegada", columnDefinition = "DATE")
    private Date fechaLlegada;

    @Column(name = "fecha_salida", columnDefinition = "DATE")
    private Date fechaSalida;
    private Integer cantidadPersonas;

    @Enumerated(EnumType.STRING)
    private ReservationStatus estadoReserva;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    public enum ReservationStatus {
        Confirmada,
        Cancelada,
        Completada,
        Pendiente    }
}

