package com.campusland.respository;

import com.campusland.respository.models.Impuesto;

public interface RepositorioImpuesto {

    Impuesto impuesto_id (int id);
    Impuesto buscarGetImpuestoPorAnio(int anio);
}
