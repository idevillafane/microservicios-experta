package com.expertamicroservices.seguridadservice.repository;

import com.expertamicroservices.seguridadservice.entity.Seguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguridadRepository extends JpaRepository<Seguridad, Integer> {

    List<Seguridad> findByTeamId(int userId);
}
