package com.cibertec.edu.Facturacion_service.endpoint;

import com.cibertec.edu.Facturacion_service.service.PagosService;
import com.cibertec.edu.Facturacion_service.ws.GetAllPagosRequest;
import com.cibertec.edu.Facturacion_service.ws.GetAllPagosResponse;
import com.cibertec.edu.Facturacion_service.ws.GetPagoByIdRequest;
import com.cibertec.edu.Facturacion_service.ws.GetPagoByIdResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PagoEndPoint {
    private static final String NAMESPACE_URI = "http://cibertec.edu/pagos/ws";

    private final PagosService pagosService;

    public PagoEndPoint(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPagoByIdRequest")
    @ResponsePayload
    public GetPagoByIdResponse getPagoById(@RequestPayload GetPagoByIdRequest request) {
        // Log para verificar que idReserva no sea null
        System.out.println("DEBUG: idReserva recibido en endpoint SOAP: " + request.getIdReserva());

        GetPagoByIdResponse response = new GetPagoByIdResponse();
        response.setPago(pagosService.generarPagoDesdeReservaRemota(request.getIdReserva()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPagosRequest")
    @ResponsePayload
    public GetAllPagosResponse getAllPagos(@RequestPayload GetAllPagosRequest request) {
        GetAllPagosResponse response = new GetAllPagosResponse();
        response.setPagos(pagosService.listarPagos());
        return response;
    }
}
