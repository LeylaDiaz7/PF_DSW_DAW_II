package com.cibertec.edu.Facturacion_service.repository;

import com.cibertec.edu.Facturacion_service.model.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagosRepository extends JpaRepository<Pagos, Long> {
    List<Pagos> findByIdReserva(Long idReserva);
}
