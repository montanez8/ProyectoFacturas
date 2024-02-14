package com.campusland.respository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.campusland.exceptiones.facturaexceptions.FacturaExceptionInsertDataBase;
import com.campusland.respository.models.Factura;

public interface RepositoryFactura {

    List<Factura> listar();

    void crear(Factura factura)throws FacturaExceptionInsertDataBase;


    List<Map<String, Object>> listarClientesPorCompras() throws SQLException;

    List<Map<String, Object>> listarProductosMasVendidos() throws SQLException;
}
