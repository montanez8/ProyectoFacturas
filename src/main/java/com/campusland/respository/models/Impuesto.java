package com.campusland.respository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Impuesto {
    private int id;
    private int anio;
    private double porcentaje;

    public Impuesto(int anio, double porcentaje) {
        this.anio = anio;
        this.porcentaje = porcentaje;
    }



}
