package com.cibertec.edu.Facturacion_service.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "idReserva" })
@XmlRootElement(name = "getPagoByIdRequest", namespace = "http://cibertec.edu/pagos/ws")
public class GetPagoByIdRequest {

    @XmlElement(namespace = "http://cibertec.edu/pagos/ws", required = true)
    private Long idReserva;

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }
}
