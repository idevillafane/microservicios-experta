package com.expertamicroservices.teamservice.service;

import com.expertamicroservices.teamservice.entity.Team;
import com.expertamicroservices.teamservice.feignclients.ComercialFeignClient;
import com.expertamicroservices.teamservice.feignclients.SeguridadFeignClient;
import com.expertamicroservices.teamservice.model.Comercial;
import com.expertamicroservices.teamservice.model.Seguridad;
import com.expertamicroservices.teamservice.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ComercialFeignClient comercialFeignClient;

    @Autowired
    SeguridadFeignClient seguridadFeignClient;

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public Team getTeamById(int id) {
        return teamRepository.findById(id).orElse(null);
    }

    public Team save(Team team) {
        Team teamNew = teamRepository.save(team);
        return teamNew;
    }

    public List<Comercial> getComerciales(int teamId) {
        List<Comercial> comerciales = restTemplate.getForObject("http://comerciale-service/comerciale/byteam/" + teamId, List.class);
        return comerciales;
    }

    public List<Seguridad> getSeguridades(int teamId) {
        List<Seguridad> seguridades = restTemplate.getForObject("http://seguridad-service/seguridad/byteam/" + teamId, List.class);
        return seguridades;
    }

    public Comercial saveComercial(int teamId, Comercial comerciale) {
        comerciale.setTeamId(teamId);
        Comercial comercialNew = comercialFeignClient.save(comerciale);
        return comercialNew;
    }

    public Seguridad saveSeguridad(int teamId, Seguridad seguridad) {
        seguridad.setTeamId(teamId);
        Seguridad seguridadNew = seguridadFeignClient.save(seguridad);
        return seguridadNew;
    }

    public Map<String, Object> getRRHH(int teamId) {
        Map<String, Object> result = new HashMap<>();
        Team team = teamRepository.findById(teamId).orElse(null);
        if(team == null) {
            result.put("Mensaje", "no existe el equipo");
            return result;
        }
        result.put("Team", team);
        List<Comercial> comerciales = comercialFeignClient.getComerciales(teamId);
        if(comerciales.isEmpty())
            result.put("Comerciales", "ese team no tiene asignado personal de Comercial");
        else
            result.put("Comerciales", comerciales);
        List<Seguridad> seguridades = seguridadFeignClient.getSeguridades(teamId);
        if(seguridades.isEmpty())
            result.put("Seguridad Informática", "ese team no tiene asignado personal de Seguridad");
        else
            result.put("Seguridad", seguridades);
        return result;
    }
}
