package com.cibertec.hotel.repository;

import com.cibertec.hotel.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    List<Habitacion> findByNumeroHabitacionContaining(String numeroHabitacion);
    boolean existsByNumeroHabitacion(String numeroHabitacion);
}


