package com.cibertec.hotel.controller;

import com.cibertec.hotel.entity.Habitacion;
import com.cibertec.hotel.service.HabitacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones") // <-- Endpoint BASE para REST
public class HabitacionRestController {

    @Autowired
    private HabitacionService habitacionService;

    // LISTAR todas las habitaciones
    @GetMapping
    public List<Habitacion> obtenerHabitaciones() {
        return habitacionService.findAll();
    }

    // BUSCAR habitaciones por número
    @GetMapping("/buscar")
    public List<Habitacion> buscarHabitaciones(@RequestParam("q") String q) {
        return habitacionService.buscarPorNumero(q);
    }

    // CREAR una habitación
    @PostMapping
    public ResponseEntity<String> crearHabitacion(@RequestBody Habitacion habitacion) {
        habitacionService.guardar(habitacion);
        return ResponseEntity.ok("Habitación creada correctamente");
    }

    // ACTUALIZAR una habitación
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarHabitacion(@PathVariable Integer id,
                                                       @RequestBody Habitacion habitacion) {
        Habitacion existente = habitacionService.obtenerPorId(id);
        if (existente == null) {
            return ResponseEntity.status(404).body("Habitación con id " + id + " no encontrada");
        }
        habitacionService.actualizarHabitacion(id, habitacion);
        return ResponseEntity.ok("Habitación actualizada correctamente");
    }

    // ELIMINAR una habitación
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Integer id) {
        Habitacion existente = habitacionService.obtenerPorId(id);
        if (existente == null) {
            return ResponseEntity.status(404).body("Habitación con id " + id + " no encontrada");
        }
        habitacionService.eliminar(id);
        return ResponseEntity.ok("Habitación eliminada correctamente");
    }

    // OBTENER detalles de una habitación
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHabitacionPorId(@PathVariable Integer id) {
        Habitacion habitacion = habitacionService.obtenerPorId(id);
        if (habitacion == null) {
            return ResponseEntity.status(404).body("Habitación con id " + id + " no encontrada");
        }
        return ResponseEntity.ok(habitacion);
    }

    //actualizar estado de la habitacion

    @PutMapping("/{id}/estado")
    public ResponseEntity<String> actualizarEstadoHabitacion(@PathVariable Integer id,
                                                             @RequestParam("estado") String estado) {
        Habitacion habitacion = habitacionService.obtenerPorId(id);
        if (habitacion == null) {
            return ResponseEntity.status(404).body("Habitación no encontrada");
        }

        habitacion.setEstado(estado);
        habitacionService.guardar(habitacion);
        return ResponseEntity.ok("Estado de la habitación actualizado a: " + estado);
    }


}
