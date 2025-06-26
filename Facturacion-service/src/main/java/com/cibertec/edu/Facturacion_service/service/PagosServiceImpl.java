package com.cibertec.edu.Facturacion_service.service;

import com.cibertec.edu.Facturacion_service.model.Pagos;
import com.cibertec.edu.Facturacion_service.repository.PagosRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PagosServiceImpl implements PagosService {

    private final PagosRepository pagosRepository;

    public PagosServiceImpl(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }

    @Override
    public Pagos registrarPago(Pagos pago) {
        return pagosRepository.save(pago);
    }

    @Override
    public List<Pagos> listarPagos() {
        return pagosRepository.findAll();
    }

    @Override
    public List<Pagos> listarPagosPorReserva(Long idReserva) {
        return pagosRepository.findByIdReserva(idReserva);
    }

    @Override
    public Pagos buscarPorId(Long idPago) {
        return pagosRepository.findById(idPago).orElse(null);
    }
}
