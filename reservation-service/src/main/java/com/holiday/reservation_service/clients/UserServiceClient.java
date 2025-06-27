package com.holiday.reservation_service.clients;

import com.holiday.reservation_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "apiusuarios", path = "/usuarios", url = "http://localhost:8002")
public interface UserServiceClient {
    @GetMapping("/{id}")
    User getUserById(@PathVariable Integer id);

//    @PostMapping("/validate")
//    Boolean validateUser(@RequestBody UserValidationRequest request);

}
