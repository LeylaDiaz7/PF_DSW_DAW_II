package com.holiday.reservation_service.clients;

import com.holiday.reservation_service.model.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@FeignClient(name = "room-service", path = "/api/rooms", url = "http://localhost:8001")
public interface RoomServiceClient {

    @GetMapping("/{id}")
    Room getRoomById(@PathVariable Integer id);

    @PutMapping("/{id}/status")
    void updateRoomStatus(
            @PathVariable Integer id,
            @RequestParam String status // "Reservado" o "Disponible"
    );

    @GetMapping("/available")
    List<Room> getAvailableRooms(
            @RequestParam Date checkIn,
            @RequestParam Date checkOut
    );
}