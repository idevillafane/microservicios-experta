package com.expertamicroservices.seguridadservice.service;

import com.expertamicroservices.seguridadservice.entity.Seguridad;
import com.expertamicroservices.seguridadservice.repository.SeguridadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguridadService {

    @Autowired
    SeguridadRepository seguridadRepository;

    public List<Seguridad> getAll() {
        return seguridadRepository.findAll();
    }

    public Seguridad getSeguridadById(int id) {
        return seguridadRepository.findById(id).orElse(null);
    }

    public Seguridad save(Seguridad seguridad) {
        Seguridad seguridadNew = seguridadRepository.save(seguridad);
        return seguridadNew;
    }

    public List<Seguridad> byTeamId(int teamId) {
        return seguridadRepository.findByTeamId(teamId);
    }
}
