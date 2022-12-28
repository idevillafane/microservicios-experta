package com.expertamicroservices.seguridadservice.controller;

import com.expertamicroservices.seguridadservice.entity.Seguridad;
import com.expertamicroservices.seguridadservice.service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seguridad")
public class SeguridadController {

    @Autowired
    SeguridadService seguridadService;

    @GetMapping
    public ResponseEntity<List<Seguridad>> getAll() {
        List<Seguridad> seguridades = seguridadService.getAll();
        if(seguridades.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(seguridades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seguridad> getById(@PathVariable("id") int id) {
        Seguridad seguridad = seguridadService.getSeguridadById(id);
        if(seguridad == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(seguridad);
    }

    @PostMapping()
    public ResponseEntity<Seguridad> save(@RequestBody Seguridad seguridad) {
        Seguridad seguridadNew = seguridadService.save(seguridad);
        return ResponseEntity.ok(seguridadNew);
    }

    @GetMapping("/byteam/{teamId}")
    public ResponseEntity<List<Seguridad>> getByTeamId(@PathVariable("teamId") int teamId) {
        List<Seguridad> seguridades = seguridadService.byTeamId(teamId);
        return ResponseEntity.ok(seguridades);
    }

}
