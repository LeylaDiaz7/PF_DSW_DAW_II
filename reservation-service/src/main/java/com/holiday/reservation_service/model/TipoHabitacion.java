package com.holiday.reservation_service.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tipos_habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;

    private String nombre;

    private String descripcion;

    private int capacidad;

    @Column(name = "precio_base")
    private BigDecimal precioBase;

    @OneToMany(mappedBy = "tipoHabitacion")
    private List<Room> habitaciones;

}
