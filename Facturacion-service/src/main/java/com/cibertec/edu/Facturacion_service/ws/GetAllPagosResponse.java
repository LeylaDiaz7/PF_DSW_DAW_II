package com.cibertec.edu.Facturacion_service.ws;

import com.cibertec.edu.Facturacion_service.model.Pagos;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name ="getAllPagosResponse", namespace ="http://cibertec.edu/pagos/ws")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllPagosResponse {

    @XmlElement(name ="pago")
    private List<Pagos> pagos = new ArrayList<>();

    public List<Pagos> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pagos> pagos) {
        this.pagos = pagos;
    }
}
