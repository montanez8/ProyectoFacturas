package com.campusland.services;

import com.campusland.respository.models.Impuesto;

public interface ServiceImpuesto {

    Impuesto impuesto_id (int id);
    Impuesto buscarGetImpuestoPorAnio(int anio);
}
