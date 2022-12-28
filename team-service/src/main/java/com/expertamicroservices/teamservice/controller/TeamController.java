package com.expertamicroservices.teamservice.controller;

import com.expertamicroservices.teamservice.entity.Team;
import com.expertamicroservices.teamservice.model.Comercial;
import com.expertamicroservices.teamservice.model.Seguridad;
import com.expertamicroservices.teamservice.service.TeamService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAll() {
        List<Team> teams = teamService.getAll();
        if(teams.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable("id") int id) {
        Team team = teamService.getTeamById(id);
        if(team == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(team);
    }

    @PostMapping()
    public ResponseEntity<Team> save(@RequestBody Team team) {
        Team teamNew = teamService.save(team);
        return ResponseEntity.ok(teamNew);
    }

    @CircuitBreaker(name = "comercialCB", fallbackMethod = "fallBackGetComerciales")
    @GetMapping("/comerciales/{teamId}")
    public ResponseEntity<List<Comercial>> getComerciales(@PathVariable("teamId") int teamId) {
        Team team = teamService.getTeamById(teamId);
        if(team == null)
            return ResponseEntity.notFound().build();
        List<Comercial> comerciales = teamService.getComerciales(teamId);
        return ResponseEntity.ok(comerciales);
    }

    @CircuitBreaker(name = "comercialCB", fallbackMethod = "fallBackSaveComercial")
    @PostMapping("/savecomercial/{teamId}")
    public ResponseEntity<Comercial> saveComercial(@PathVariable("teamId") int teamId, @RequestBody Comercial comercial) {
        if(teamService.getTeamById(teamId) == null)
            return ResponseEntity.notFound().build();
        Comercial comercialNew = teamService.saveComercial(teamId, comercial);
        return ResponseEntity.ok(comercial);
    }

    @CircuitBreaker(name = "seguridadCB", fallbackMethod = "fallBackGetSeguridades")
    @GetMapping("/seguridades/{teamId}")
    public ResponseEntity<List<Seguridad>> getSeguridades(@PathVariable("teamId") int teamId) {
        Team team = teamService.getTeamById(teamId);
        if(team == null)
            return ResponseEntity.notFound().build();
        List<Seguridad> seguridades = teamService.getSeguridades(teamId);
        return ResponseEntity.ok(seguridades);
    }

    @CircuitBreaker(name = "seguridadCB", fallbackMethod = "fallBackSaveSeguridad")
    @PostMapping("/saveseguridad/{teamId}")
    public ResponseEntity<Seguridad> saveSeguridad(@PathVariable("teamId") int teamId, @RequestBody Seguridad seguridad) {
        if(teamService.getTeamById(teamId) == null)
            return ResponseEntity.notFound().build();
        Seguridad seguridadNew = teamService.saveSeguridad(teamId, seguridad);
        return ResponseEntity.ok(seguridad);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
    @GetMapping("/getAll/{teamId}")
    public ResponseEntity<Map<String, Object>> getAllRRHH(@PathVariable("teamId") int teamId) {
        Map<String, Object> result = teamService.getRRHH(teamId);
        return ResponseEntity.ok(result);
    }

    private ResponseEntity<List<Comercial>> fallBackGetComerciales(@PathVariable("teamId") int teamId, RuntimeException e) {
        return new ResponseEntity("El equipo/team " + teamId + " está sin personal de Comercial activo", HttpStatus.OK);
    }

    private ResponseEntity<Comercial> fallBackSaveComercial(@PathVariable("teamId") int teamId, @RequestBody Comercial comercial, RuntimeException e) {
        return new ResponseEntity("El equipo/team " + teamId + " no puede sumar nuevo personal de Comercial", HttpStatus.OK);
    }

    private ResponseEntity<List<Seguridad>> fallBackGetSeguridades(@PathVariable("teamId") int teamId, RuntimeException e) {
        return new ResponseEntity("El equipo/team " + teamId + " no tiene personal de Seguridad Informática activo", HttpStatus.OK);
    }

    private ResponseEntity<Comercial> fallBackSaveSeguridad(@PathVariable("teamId") int teamId, @RequestBody Seguridad seguridad, RuntimeException e) {
        return new ResponseEntity("El equipo/team " + teamId + "  no puede sumar nuevo personal de Seguridad Informática", HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> fallBackGetAll(@PathVariable("teamId") int teamId, RuntimeException e) {
        return new ResponseEntity("El equipo/team " + teamId + " no cuenta con personal activo", HttpStatus.OK);
    }

}
