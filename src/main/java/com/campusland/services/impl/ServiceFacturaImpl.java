package com.campusland.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.campusland.exceptiones.facturaexceptions.FacturaExceptionInsertDataBase;
import com.campusland.respository.RepositoryFactura;
import com.campusland.respository.models.Factura;
import com.campusland.services.ServiceFactura;

public class ServiceFacturaImpl implements ServiceFactura {

  private final RepositoryFactura repositoryFacturaMysql;
  private final RepositoryFactura repositoryFacturaJson;

  public ServiceFacturaImpl(RepositoryFactura repositoryFacturaMysql, RepositoryFactura repositoryFacturaJson) {
    this.repositoryFacturaMysql = repositoryFacturaMysql;
    this.repositoryFacturaJson = repositoryFacturaJson;
  }

  @Override
  public List<Factura> listar() {
    return this.repositoryFacturaMysql.listar();

  }

  @Override
  public void crear(Factura factura) {
      try {
          guardarEnMysqlYJson(factura);
      } catch (FacturaExceptionInsertDataBase e) {
         e.printStackTrace();
      }
  }

    @Override
    public List<Map<String, Object>> listarClientesPorCompras() throws SQLException {
        return this.repositoryFacturaMysql.listarClientesPorCompras();
    }

    @Override
    public List<Map<String, Object>> listarProductosMasVendidos() throws SQLException {
    return this.repositoryFacturaMysql.listarProductosMasVendidos();
    }

    private void guardarEnMysqlYJson(Factura factura) throws FacturaExceptionInsertDataBase {
      guardarEnMysql(factura);
      guardarEnJson(factura);
  }
  
  private void guardarEnMysql(Factura factura) throws FacturaExceptionInsertDataBase {
      this.repositoryFacturaMysql.crear(factura);
  }
  
  private void guardarEnJson(Factura factura) throws FacturaExceptionInsertDataBase {
      try {
          this.repositoryFacturaJson.crear(factura);
      } catch (FacturaExceptionInsertDataBase eJson) {
          eJson.printStackTrace();
      }
  }
  

}
