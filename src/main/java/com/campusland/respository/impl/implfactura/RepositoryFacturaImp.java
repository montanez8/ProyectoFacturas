package com.campusland.respository.impl.implfactura;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.campusland.respository.RepositoryFactura;
import com.campusland.respository.models.Factura;
import com.campusland.utils.conexionpersistencia.conexiondblist.ConexionBDList;

public class RepositoryFacturaImp implements RepositoryFactura {

    ConexionBDList conexion = ConexionBDList.getConexion();

    @Override
    public List<Factura> listar() {
        return conexion.getListFacturas();
        
    }

    @Override
    public void crear(Factura factura) {
       conexion.getListFacturas().add(factura);
        
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
