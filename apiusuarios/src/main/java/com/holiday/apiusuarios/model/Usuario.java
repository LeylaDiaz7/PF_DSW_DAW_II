package com.holiday.apiusuarios.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idUsuario;

        private String nombre;

        private String apellido;

        private String email;

        private String contraseña;

        private String rol;
}
