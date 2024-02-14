package com.campusland.respository.impl.implfactura;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.campusland.exceptiones.facturaexceptions.FacturaExceptionInsertDataBase;
import com.campusland.respository.RepositoryFactura;
import com.campusland.respository.models.Factura;
import com.campusland.utils.conexionpersistencia.conexionbdjson.ConexionBDJsonFacturas;

public class RepositoryFacturaJsonImpl implements RepositoryFactura {

    ConexionBDJsonFacturas conexion = ConexionBDJsonFacturas.getConexion();

    @Override
    public List<Factura> listar() {
        return conexion.getData(Factura.class);
    }

    @Override
    public void crear(Factura factura) throws FacturaExceptionInsertDataBase {
        List<Factura> listFacturas = conexion.getData(Factura.class);
        listFacturas.add(factura);
        conexion.saveData(listFacturas);
       
    }

    @Override
    public List<Map<String, Object>> listarClientesPorCompras() throws SQLException {
        return null;
    }

    @Override
    public List<Map<String, Object>> listarProductosMasVendidos() throws SQLException {
        return null;
    }

}
