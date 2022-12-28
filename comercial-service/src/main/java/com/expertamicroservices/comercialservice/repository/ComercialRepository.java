package com.expertamicroservices.comercialservice.repository;

import com.expertamicroservices.comercialservice.entity.Comercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComercialRepository extends JpaRepository<Comercial, Integer> {

    List<Comercial> findByTeamId(int teamId);
}
