package com.expertamicroservices.comercialservice.service;

import com.expertamicroservices.comercialservice.entity.Comercial;
import com.expertamicroservices.comercialservice.repository.ComercialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComercialService {

    @Autowired
    ComercialRepository comercialRepository;

    public List<Comercial> getAll() {
        return comercialRepository.findAll();
    }

    public Comercial getComercialById(int id) {
        return comercialRepository.findById(id).orElse(null);
    }

    public Comercial save(Comercial bike) {
        Comercial bikeNew = comercialRepository.save(bike);
        return bikeNew;
    }

    public List<Comercial> byTeamId(int teamId) {
        return comercialRepository.findByTeamId(teamId);
    }
}
