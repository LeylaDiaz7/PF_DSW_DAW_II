package com.holiday.reservation_service.service;

import com.holiday.reservation_service.clients.RoomServiceClient;
import com.holiday.reservation_service.clients.UserServiceClient;
import com.holiday.reservation_service.dto.ReservationDTO;
import com.holiday.reservation_service.dto.ReservationResponseDTO;
import com.holiday.reservation_service.exception.ReservationException;
import com.holiday.reservation_service.model.Reservation;
import com.holiday.reservation_service.model.Room;
import com.holiday.reservation_service.model.User;
import com.holiday.reservation_service.repository.ReservationRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservaRepository;
    private final RoomServiceClient habitacionService;
    private final UserServiceClient usuarioService;

    public ReservationService(ReservationRepository reservaRepository,
                              RoomServiceClient habitacionService,
                              UserServiceClient usuarioService) {
        this.reservaRepository = reservaRepository;
        this.habitacionService = habitacionService;
        this.usuarioService = usuarioService;
    }

    public ReservationResponseDTO crearReserva(ReservationDTO dto) {
        boolean ocupada = reservaRepository.existsByRoom_IdHabitacionAndFechaLlegadaLessThanAndFechaSalidaGreaterThan(
                dto.idHabitacion(), dto.fechaSalida(), dto.fechaLlegada());

        if (ocupada) {
            throw new RuntimeException("La habitación ya está ocupada en ese rango de fechas.");
        }

        User user = usuarioService.getUserById(dto.idUsuario());
        Room room = habitacionService.getRoomById(dto.idHabitacion());

        validarFechas(dto.fechaLlegada(), dto.fechaSalida());

        Reservation reserva = new Reservation();
        reserva.setUser(user);
        reserva.setRoom(room);
        reserva.setFechaLlegada(dto.fechaLlegada());
        reserva.setFechaSalida(dto.fechaSalida());
        reserva.setCantidadPersonas(dto.cantidadPersonas());
        reserva.setEstadoReserva(Reservation.ReservationStatus.Confirmada);
        reserva.setTotal(dto.total());
        reserva.setFechaCreacion(new Date());

        Reservation saved = reservaRepository.save(reserva);

        try {
            habitacionService.updateRoomStatus(room.getIdHabitacion(), Room.EstadoHabitacion.Ocupada.name());
        } catch (FeignException e) {
            throw new ReservationException("Error al marcar la habitación como ocupada");
        }

        return convertirAResponseDTO(saved);
    }

    @Transactional
    public ReservationResponseDTO cancelarReserva(Integer reservaId) {

        Reservation reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new ReservationException("Reserva no encontrada con ID: " + reservaId));

        if (reserva.getEstadoReserva() == Reservation.ReservationStatus.Cancelada) {
            throw new ReservationException("La reserva ya está cancelada");
        }

        Date hoy = new Date();
        if (hoy.after(reserva.getFechaLlegada())) {
            throw new ReservationException("No se puede cancelar una reserva después de la fecha de llegada");
        }

        try {
            habitacionService.updateRoomStatus(reserva.getRoom().getIdHabitacion(), Room.EstadoHabitacion.Disponible.name());
        } catch (FeignException e) {
            throw new ReservationException("Error al actualizar el estado de la habitación");
        }

        reserva.setEstadoReserva(Reservation.ReservationStatus.Cancelada);
        reserva.setFechaCreacion(new Date());

        Reservation reservaCancelada = reservaRepository.save(reserva);

        return convertirAResponseDTO(reservaCancelada);
    }

    public List<ReservationResponseDTO> obtenerReservasPorUsuario(Integer usuarioId) {
        return reservaRepository.buscarPorIdUsuario(usuarioId)
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }


    private void validarFechas(Date fechaLlegada, Date fechaSalida) {
        if (fechaSalida.before(fechaLlegada) || fechaSalida.equals(fechaLlegada)) {
            throw new ReservationException("La fecha de salida debe ser posterior a la fecha de llegada");
        }
    }

    private ReservationResponseDTO convertirAResponseDTO(Reservation reserva) {
        return new ReservationResponseDTO(
                reserva.getIdReserva(),
                reserva.getRoom().getNumeroHabitacion(),
                reserva.getRoom().getTipoHabitacion().getNombre(),
                reserva.getFechaLlegada(),
                reserva.getFechaSalida(),
                reserva.getCantidadPersonas(),
                reserva.getEstadoReserva().name(),
                reserva.getTotal(),
                reserva.getFechaCreacion()
        );
    }


    public List<ReservationResponseDTO> listarTodasLasReservas() {
        return reservaRepository.findAll().stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }
}