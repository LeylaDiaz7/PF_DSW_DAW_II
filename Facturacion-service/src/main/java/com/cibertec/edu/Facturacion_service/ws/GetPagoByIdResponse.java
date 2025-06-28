package com.cibertec.edu.Facturacion_service.ws;

import com.cibertec.edu.Facturacion_service.model.Pagos;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "getPagoByIdResponse", namespace = "http://cibertec.edu/pagos/ws")
public class GetPagoByIdResponse {

    private Pagos pago; // ajusta según tu modelo

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }
}
