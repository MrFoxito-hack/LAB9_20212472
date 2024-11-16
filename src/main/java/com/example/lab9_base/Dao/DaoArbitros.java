//Dao arbitros - Miguel Angel Alvizuri Yucra 20212472

//cabe resaltar que el boton borrar si funciona, puede agregar un arbitro y borrarlo. sin embargo no funcionara
//para los que ya estaban en la base de datos como se menciono en las indicaciones

package com.example.lab9_base.Dao;



import com.example.lab9_base.Bean.Arbitro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoArbitros extends DaoBase {

    // Metodo para listar todos los arbitros y mostrarlo en la vista list
    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "SELECT * FROM arbitro";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Iteramos
            while (rs.next()) {
                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt("idArbitro"));
                arbitro.setNombre(rs.getString("nombre"));
                arbitro.setPais(rs.getString("pais"));
                arbitros.add(arbitro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arbitros;
    }

    // Metodo para crear un nuevo árbitro
    public void crearArbitro(Arbitro arbitro) {
        // Antes de crear, verificar si ya existe un árbitro con el mismo nombre
        if (existeArbitroConNombre(arbitro.getNombre())) {
            throw new IllegalArgumentException("Ya existe un árbitro con ese nombre.");
        }

        String sql = "INSERT INTO arbitro (nombre, pais) VALUES (?, ?)";  // Consulta para insertar un árbitro

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {


            stmt.setString(1, arbitro.getNombre());
            stmt.setString(2, arbitro.getPais());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Meotodo para verificar si ya existe un arebitro con el mismo nombre (indicaciones)
    private boolean existeArbitroConNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM arbitro WHERE nombre = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Si hay más de 0, ya existe un árbitro con ese nombre
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //como vamos a implemeentar el drop opcional creamos metodo para buscar por pais o por nombre

    // metodo buscar por pais
    public ArrayList<Arbitro> busquedaPais(String pais) {
        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "SELECT * FROM arbitro WHERE pais LIKE ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "%" + pais + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt("idArbitro"));
                arbitro.setNombre(rs.getString("nombre"));
                arbitro.setPais(rs.getString("pais"));
                arbitros.add(arbitro); //agregamos el arbitro creado en la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arbitros;
    }

    // Meotdo buscar por nombre
    public ArrayList<Arbitro> busquedaNombre(String nombre) {
        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "SELECT * FROM arbitro WHERE nombre LIKE ?";  // Consulta para buscar por nombre

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt("idArbitro"));
                arbitro.setNombre(rs.getString("nombre"));
                arbitro.setPais(rs.getString("pais"));
                arbitros.add(arbitro);  //agregamos el arbitro creado en la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arbitros;
    }

    // Metodo para buscar arbitro
    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = null;
        String sql = "SELECT * FROM arbitro WHERE idArbitro = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt("idArbitro"));
                arbitro.setNombre(rs.getString("nombre"));
                arbitro.setPais(rs.getString("pais"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arbitro;
    }

    // Metodo para borrar arbitro
    public void borrarArbitro(int id) {
        String sql = "DELETE FROM arbitro WHERE idArbitro = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para listar paises en el formulario
    public ArrayList<String> listarPaises() {
        ArrayList<String> paises = new ArrayList<>();
        String sql = "SELECT DISTINCT pais FROM arbitro";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                paises.add(rs.getString("pais"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paises;
    }
}
