package com.expertamicroservices.comercialservice.controller;

import com.expertamicroservices.comercialservice.entity.Comercial;
import com.expertamicroservices.comercialservice.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comercial")
public class ComercialController {

    @Autowired
    ComercialService comercialService;

    @GetMapping
    public ResponseEntity<List<Comercial>> getAll() {
        List<Comercial> comerciales = comercialService.getAll();
        if(comerciales.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(comerciales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comercial> getById(@PathVariable("id") int id) {
        Comercial comercial = comercialService.getComercialById(id);
        if(comercial == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(comercial);
    }

    @PostMapping()
    public ResponseEntity<Comercial> save(@RequestBody Comercial comercial) {
        Comercial comercialNew = comercialService.save(comercial);
        return ResponseEntity.ok(comercialNew);
    }

    @GetMapping("/byteam/{teamId}")
    public ResponseEntity<List<Comercial>> getByTeamId(@PathVariable("teamId") int teamId) {
        List<Comercial> comerciales = comercialService.byTeamId(teamId);
        return ResponseEntity.ok(comerciales);
    }

}
