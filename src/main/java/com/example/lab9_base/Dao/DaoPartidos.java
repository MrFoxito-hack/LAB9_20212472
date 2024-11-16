//Dao Partidos - Miguel Angel Alvizuri Yucra 20212472
package com.example.lab9_base.Dao;



import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Bean.Estadio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase {

    // metodo para lisatr artidos y luego listar en el index que es el predeterminado al ejecutarr
    public ArrayList<Partido> listaDePartidos() {
        ArrayList<Partido> partidos = new ArrayList<>();

        String sql = "SELECT p.*, s1.nombre AS nombreLocal, s2.nombre AS nombreVisitante, a.nombre AS nombreArbitro, " +
                "e.idEstadio AS idEstadio, e.nombre AS nombreEstadio, e.provincia AS provincia, e.club AS club " +
                "FROM partido p " +
                "JOIN seleccion s1 ON p.seleccionLocal = s1.idSeleccion " +
                "JOIN seleccion s2 ON p.seleccionVisitante = s2.idSeleccion " +
                "JOIN arbitro a ON p.arbitro = a.idArbitro " +
                "JOIN estadio e ON s1.estadio_idEstadio = e.idEstadio";

        // hacemos conexion a la base de datos
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Iteracmos
            while (rs.next()) {
                Partido partido = new Partido();

                partido.setIdPartido(rs.getInt("idPartido"));
                partido.setFecha(rs.getDate("fecha"));
                partido.setNumeroJornada(rs.getInt("numeroJornada"));

                Seleccion seleccionLocal = new Seleccion();
                seleccionLocal.setIdSeleccion(rs.getInt("seleccionLocal"));
                seleccionLocal.setNombre(rs.getString("nombreLocal"));
                partido.setSeleccionLocal(seleccionLocal);

                Seleccion seleccionVisitante = new Seleccion();
                seleccionVisitante.setIdSeleccion(rs.getInt("seleccionVisitante"));
                seleccionVisitante.setNombre(rs.getString("nombreVisitante"));
                partido.setSeleccionVisitante(seleccionVisitante);

                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt("arbitro"));
                arbitro.setNombre(rs.getString("nombreArbitro"));
                partido.setArbitro(arbitro);

                Estadio estadioLocal = new Estadio();
                estadioLocal.setIdEstadio(rs.getInt("idEstadio"));
                estadioLocal.setNombre(rs.getString("nombreEstadio"));
                estadioLocal.setProvincia(rs.getString("provincia"));
                estadioLocal.setClub(rs.getString("club"));
                seleccionLocal.setEstadio(estadioLocal);

                // Agregamos el partido en la lista
                partidos.add(partido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidos;
    }



    // Metodo para agregar un nuevo partido en la base de datos
    public void crearPartido(Partido partido) {
        String sql = "INSERT INTO partido (seleccionLocal, seleccionVisitante, arbitro, fecha, numeroJornada) " +
                "VALUES (?, ?, ?, ?, ?)";

        // nos conectamos apra insertar
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // asignacion de los valores
            stmt.setInt(1, partido.getSeleccionLocal().getIdSeleccion());
            stmt.setInt(2, partido.getSeleccionVisitante().getIdSeleccion());
            stmt.setInt(3, partido.getArbitro().getIdArbitro());
            stmt.setDate(4, partido.getFecha());
            stmt.setInt(5, partido.getNumeroJornada());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

