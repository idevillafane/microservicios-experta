package com.expertamicroservices.teamservice.feignclients;

import com.expertamicroservices.teamservice.model.Seguridad;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "seguridad-service")
@RequestMapping("/seguridad")
public interface SeguridadFeignClient {

    @PostMapping()
    Seguridad save(@RequestBody Seguridad seguridad);

    @GetMapping("/byteam/{teamId}")
    List<Seguridad> getSeguridades(@PathVariable("teamId") int teamId);
}
