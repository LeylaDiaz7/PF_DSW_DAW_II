package com.cibertec.edu.Facturacion_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "pagos")
public class Pagos {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_pago")
        private Long idPagos;

        @Column(name = "id_reserva")
        private Integer idReserva;

        private double monto;

        @Enumerated(EnumType.STRING)
        private MetodoPago metodoPago;

        @Enumerated(EnumType.STRING)
        private EstadoPago estadoPago;

        private Timestamp fechaPago;

        @PrePersist
        public void prePersist() {
            this.fechaPago = new Timestamp(System.currentTimeMillis());

        }
}
