package com.cibertec.edu.Facturacion_service.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "pago")
@XmlAccessorType(XmlAccessType.FIELD)
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

        @XmlElement
        private String fechaPago; // ✅ Ahora como String

        @PrePersist
        public void prePersist() {
                this.fechaPago = ZonedDateTime.now()
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME); // ⬅️ formato ISO 8601
        }
}
