package com.holiday.reservation_service.repository;

import com.holiday.reservation_service.model.Reservation;
import com.holiday.reservation_service.model.User;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.user.idUsuario = :idUsuario")
    List<Reservation> buscarPorIdUsuario(@Param("idUsuario") Integer idUsuario);

    boolean existsByRoom_IdHabitacionAndFechaLlegadaLessThanAndFechaSalidaGreaterThan(
            Integer idHabitacion, Date fechaSalida, Date fechaLlegada);


}
