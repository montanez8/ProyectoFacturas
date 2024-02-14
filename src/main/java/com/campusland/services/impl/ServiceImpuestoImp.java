package com.campusland.services.impl;

import com.campusland.respository.RepositorioImpuesto;
import com.campusland.respository.models.Impuesto;
import com.campusland.services.ServiceImpuesto;

public class ServiceImpuestoImp implements ServiceImpuesto {
    private final RepositorioImpuesto repositoryImpuesto;

    public ServiceImpuestoImp(RepositorioImpuesto repositoryImpuesto) {
        this.repositoryImpuesto = repositoryImpuesto;
    }

    @Override
    public Impuesto impuesto_id(int id) {
        return this.repositoryImpuesto.impuesto_id(id);
    }

    @Override
    public Impuesto buscarGetImpuestoPorAnio(int anio) {
        return this.repositoryImpuesto.buscarGetImpuestoPorAnio(anio);
    }

}
