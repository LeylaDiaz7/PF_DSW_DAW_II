package com.cibertec.hotel.service;

import com.cibertec.hotel.entity.Habitacion;
import com.cibertec.hotel.entity.TipoHabitacion;
import com.cibertec.hotel.repository.HabitacionRepository;
import com.cibertec.hotel.repository.TipoHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    public List<Habitacion> findAll() {
        return habitacionRepository.findAll();
    }

    public List<Habitacion> buscarPorNumero(String numero) {
        return habitacionRepository.findByNumeroHabitacionContaining(numero);
    }

    public Habitacion obtenerPorId(Integer id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    public Habitacion guardar(Habitacion habitacion) {
        if (habitacion.getIdHabitacion() == null) {
            // CREACIÓN
            if (habitacionRepository.existsByNumeroHabitacion(habitacion.getNumeroHabitacion())) {
                throw new RuntimeException("No se puede agregar esta habitación porque el número "
                        + habitacion.getNumeroHabitacion() + " ya existe.");
            }
        } else {
            // EDICIÓN
            Habitacion existente = obtenerPorId(habitacion.getIdHabitacion());
            if (existente != null) {
                String nuevoNumero = habitacion.getNumeroHabitacion();

                // Si cambió el numero de habitación
                if (!existente.getNumeroHabitacion().equals(nuevoNumero)) {
                    if (habitacionRepository.existsByNumeroHabitacion(nuevoNumero)) {
                        throw new RuntimeException("No se puede actualizar esta habitación porque el número "
                                + nuevoNumero + " ya existe.");
                    }
                }
            }
        }
        return habitacionRepository.save(habitacion);
    }


    public void eliminar(Integer id) {
        habitacionRepository.deleteById(id);
    }


    public Habitacion actualizarHabitacion(Integer id, Habitacion habitacionActualizada) {
        Habitacion existente = obtenerPorId(id);
        if (existente == null) {
            throw new RuntimeException("Habitación no encontrada");
        }

        // Mantener numeroHabitacion y estado actuales
        habitacionActualizada.setNumeroHabitacion(existente.getNumeroHabitacion());
        habitacionActualizada.setEstado(existente.getEstado());

        // Cambiar TipoHabitacion si llega un id válido
        if (habitacionActualizada.getTipoHabitacion() != null &&
                habitacionActualizada.getTipoHabitacion().getIdTipo() != null) {
            TipoHabitacion tipo = tipoHabitacionRepository.findById(habitacionActualizada.getTipoHabitacion().getIdTipo())
                    .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado"));
            habitacionActualizada.setTipoHabitacion(tipo);
        } else {
            habitacionActualizada.setTipoHabitacion(existente.getTipoHabitacion());
        }

        habitacionActualizada.setIdHabitacion(id);
        return habitacionRepository.save(habitacionActualizada);
    }
}
