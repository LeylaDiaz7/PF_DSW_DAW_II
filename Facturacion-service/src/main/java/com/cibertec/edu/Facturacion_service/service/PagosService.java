package com.cibertec.edu.Facturacion_service.service;

import com.cibertec.edu.Facturacion_service.model.Pagos;

import java.util.List;

public interface PagosService {

    Pagos generarPagoDesdeReservaRemota(Long idReserva);
    List<Pagos> listarPagos();
    Pagos registrarPago(Pagos pagos);
    List<Pagos> listarPagosPorReserva(Long idReserva);
    Pagos buscarPorId(Long idPago);

}
