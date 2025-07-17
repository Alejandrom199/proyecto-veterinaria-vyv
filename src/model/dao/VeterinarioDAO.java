package model.dao;

import model.database.Conexion;
import model.entities.Veterinario;
import model.exceptions.PersistenceException;
import utils.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de veterinarios en la base de datos.
 * Implementa la interfaz GenericDAO para operaciones estándar.
 *
 * @author Lenny Borbor
 * @version 1.0
 * @see GenericDAO
 */
public class VeterinarioDAO implements GenericDAO<Veterinario>{

    /**
     * Constructor por defecto.
     */
    public VeterinarioDAO() {}

    /**
     * Guarda un nuevo veterinario en la base de datos.
     *
     * @param veterinario El veterinario a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void guardar(Veterinario veterinario) throws PersistenceException {
        String sql = QueryManager.getQuery("veterinario.insert");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al guardar el veterinario",e);
        }
    }

    /**
     * Obtiene todos los veterinarios registrados.
     *
     * @return Lista de veterinarios
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public List<Veterinario> obtenerTodos() throws PersistenceException{
        List<Veterinario> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("veterinario.select.all");

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setIdVeterinario(rs.getInt("idVeterinario"));
                veterinario.setNombre(rs.getString("nombre"));
                veterinario.setEspecialidad(rs.getString("especialidad"));
                veterinario.setTelefono(rs.getString("telefono"));
                veterinario.setEmail(rs.getString("email"));
                lista.add(veterinario);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al obtener todas los veterinarios", e);
        }

        return lista;
    }

    /**
     * Busca un veterinario por su ID.
     *
     * @param id El ID del veterinario a buscar
     * @return El veterinario encontrado o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public Veterinario buscarPorId(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("veterinario.select.byId");
        Veterinario veterinario = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veterinario = new Veterinario();
                    veterinario.setIdVeterinario(rs.getInt("idVeterinario"));
                    veterinario.setNombre(rs.getString("nombre"));
                    veterinario.setEspecialidad(rs.getString("especialidad"));
                    veterinario.setTelefono(rs.getString("telefono"));
                    veterinario.setEmail(rs.getString("email"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar veterinario por ID: " + id, e);
        }

        return veterinario;
    }

    /**
     * Actualiza los datos de un veterinario existente.
     *
     * @param veterinario El veterinario con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void actualizar(Veterinario veterinario) throws PersistenceException{
        String sql = QueryManager.getQuery("veterinario.update");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());
            stmt.setInt(5, veterinario.getIdVeterinario());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al actualizar la cita", e);
        }
    }

    /**
     * Elimina un veterinario de la base de datos.
     *
     * @param id El ID del veterinario a eliminar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void eliminar(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("veterinario.delete");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al eliminar el veterinario con ID: " + id, e);
        }
    }
}