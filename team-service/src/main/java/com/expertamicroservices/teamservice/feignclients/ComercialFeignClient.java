package com.expertamicroservices.teamservice.feignclients;

import com.expertamicroservices.teamservice.model.Comercial;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "comercial-service")
@RequestMapping("/comercial")
public interface ComercialFeignClient {

    @PostMapping()
    Comercial save(@RequestBody Comercial comercial);

    @GetMapping("/byteam/{teamId}")
    List<Comercial> getComerciales(@PathVariable("teamId") int teamId);
}
