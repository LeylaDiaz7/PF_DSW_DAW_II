package com.holiday.reservation_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habitaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHabitacion;

    @Column(name = "numero_habitacion", unique = true)
    private String numeroHabitacion;

    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoHabitacion tipoHabitacion;

    public enum EstadoHabitacion {
        Disponible, Ocupada, Mantenimiento, Reservada
    }

}
