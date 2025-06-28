package com.cibertec.edu.Facturacion_service.service;

import com.cibertec.edu.Facturacion_service.dto.ReservationDTO;
import com.cibertec.edu.Facturacion_service.model.EstadoPago;
import com.cibertec.edu.Facturacion_service.model.MetodoPago;
import com.cibertec.edu.Facturacion_service.model.Pagos;
import com.cibertec.edu.Facturacion_service.repository.PagosRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PagosServiceImpl implements PagosService {

    private final PagosRepository pagosRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String RESERVA_URL = "http://localhost:8081/api/reservas/";

    public PagosServiceImpl(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }

    @Override
    public Pagos generarPagoDesdeReservaRemota(Long idReserva) {
        try {
            // Llama al microservicio de reservas
            ResponseEntity<String> response = restTemplate.getForEntity(RESERVA_URL + idReserva, String.class);
            String json = response.getBody();

            // Mapper para deserializar
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            ReservationDTO reserva = mapper.readValue(json, ReservationDTO.class);

            if (reserva == null || reserva.getTotal() == null || reserva.getFechaCreacion() == null) {
                System.err.println("Reserva inválida: " + json);
                return null;
            }

            // Parseo manual de fechaCreacion ISO-8601
            DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            OffsetDateTime fechaCreacion = OffsetDateTime.parse(reserva.getFechaCreacion(), isoFormatter);

            Pagos nuevoPago = new Pagos();
            nuevoPago.setIdReserva(reserva.getIdReserva());
            nuevoPago.setMonto(reserva.getTotal().doubleValue());
            nuevoPago.setMetodoPago(MetodoPago.Transferencia);
            nuevoPago.setEstadoPago(EstadoPago.Completado);
            nuevoPago.setFechaPago(
                    reserva.getFechaCreacion() != null
                            ? reserva.getFechaCreacion()
                            : ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            );

            return pagosRepository.save(nuevoPago);

        } catch (Exception e) {
            System.err.println("Error al generar pago desde reserva: " + e.getMessage());
            return null;
        }
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
