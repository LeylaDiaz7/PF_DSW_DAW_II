package com.cibertec.edu.Facturacion_service.controller;

import com.cibertec.edu.Facturacion_service.model.Pagos;
import com.cibertec.edu.Facturacion_service.service.PagosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    private final PagosService pagosService;

    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @PostMapping
    public ResponseEntity<Pagos> registrarPago(@RequestBody Pagos pagos) {
        return new ResponseEntity<>(pagosService.registrarPago(pagos), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Pagos> listarTodos() {
        return pagosService.listarPagos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagos> obtenerPorId(@PathVariable Long id) {
        Pagos pagos = pagosService.buscarPorId(id);
        return pagos != null ? ResponseEntity.ok(pagos) : ResponseEntity.notFound().build();
    }

    @GetMapping("/reserva/{idReserva}")
    public List<Pagos> listarPorReserva(@PathVariable Long idReserva) {
        return pagosService.listarPagosPorReserva(idReserva);
    }
}
