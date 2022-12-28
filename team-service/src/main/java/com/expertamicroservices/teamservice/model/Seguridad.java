package com.expertamicroservices.teamservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seguridad {
    private String nombre;
    private String apellido;
    private int teamId;
}
