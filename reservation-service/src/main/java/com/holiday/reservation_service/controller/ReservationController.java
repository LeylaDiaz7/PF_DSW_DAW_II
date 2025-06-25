package com.holiday.reservation_service.controller;

import com.holiday.reservation_service.dto.ReservationDTO;
import com.holiday.reservation_service.dto.ReservationResponseDTO;
import com.holiday.reservation_service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservaService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> crearReserva(@RequestBody ReservationDTO reservaDTO) {
        ReservationResponseDTO response = reservaService.crearReserva(reservaDTO);
        return ResponseEntity.ok(response);    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReservationResponseDTO>> obtenerReservasPorUsuario(@PathVariable Integer  usuarioId) {
        return ResponseEntity.ok(reservaService.obtenerReservasPorUsuario(usuarioId));
    }

    @PutMapping("/{reservaId}/cancelar")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Integer reservaId) {
        reservaService.cancelarReserva(reservaId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> listarTodasLasReservas() {
        return ResponseEntity.ok(reservaService.listarTodasLasReservas());
    }
}
