package com.holiday.reservation_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String email;

    @Column(name = "contraseña")
    private String contrasenia;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservas;

}
