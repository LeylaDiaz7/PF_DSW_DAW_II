package com.cibertec.edu.Facturacion_service.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;


@Data
@XmlRootElement(name ="getAllPagosRequest", namespace ="http://cibertec.edu/pagos/ws")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllPagosRequest {

}
