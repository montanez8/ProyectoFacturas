package com.campusland.respository.impl.impRepositoryImpuesto;

import com.campusland.respository.RepositorioImpuesto;
import com.campusland.respository.models.Impuesto;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryImpuestoImp implements RepositorioImpuesto {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public Impuesto impuesto_id(int id) {
        Impuesto impuesto = null;
        String sql = "SELECT * FROM impuesto WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    impuesto = new Impuesto();
                    impuesto.setId(rs.getInt("id"));
                    impuesto.setAnio(rs.getInt("anio"));
                    impuesto.setPorcentaje(rs.getDouble("porcentaje"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return impuesto;
    }

    @Override
    public Impuesto buscarGetImpuestoPorAnio(int anio) {
        Impuesto impuesto = null;
        String sql = "SELECT * FROM impuesto WHERE anio = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, anio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    impuesto = new Impuesto();
                    impuesto.setId(rs.getInt("id"));
                    impuesto.setAnio(rs.getInt("anio"));
                    impuesto.setPorcentaje(rs.getDouble("porcentaje"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return impuesto;

    }


}
