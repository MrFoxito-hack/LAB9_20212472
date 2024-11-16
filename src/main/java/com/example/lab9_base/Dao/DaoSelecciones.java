//Dao Partidos - Miguel Angel Alvizuri Yucra 20212472

package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Seleccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoSelecciones extends DaoBase {

    public ArrayList<Seleccion> listarSelecciones() {
        ArrayList<Seleccion> selecciones = new ArrayList<>();
        String sql = "SELECT s.*, e.idEstadio, e.nombre AS nombreEstadio, e.provincia, e.club " +
                "FROM seleccion s " +
                "JOIN estadio e ON s.estadio_idEstadio = e.idEstadio";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Seleccion seleccion = new Seleccion();
                seleccion.setIdSeleccion(rs.getInt("idSeleccion"));
                seleccion.setNombre(rs.getString("nombre"));
                seleccion.setTecnico(rs.getString("tecnico"));

                Estadio estadio = new Estadio();
                estadio.setIdEstadio(rs.getInt("idEstadio"));
                estadio.setNombre(rs.getString("nombreEstadio"));
                estadio.setProvincia(rs.getString("provincia"));
                estadio.setClub(rs.getString("club"));

                seleccion.setEstadio(estadio);
                selecciones.add(seleccion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las selecciones: " + e.getMessage());
            e.printStackTrace();
        }
        return selecciones;
    }
}
