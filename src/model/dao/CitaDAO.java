package model.dao;

import model.database.Conexion;
import model.entities.Cita;
import model.exceptions.PersistenceException;
import utils.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de citas en la base de datos.
 * Implementa la interfaz GenericDAO para operaciones estándar.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @see GenericDAO
 */
public class CitaDAO implements GenericDAO<Cita>{

    /**
     * Constructor por defecto.
     */
    public CitaDAO(){}

    /**
     * Guarda una nueva cita en la base de datos.
     *
     * @param cita La cita a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void guardar(Cita cita) throws PersistenceException {
        String sql = QueryManager.getQuery("cita.insert");

        try (
                Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        )
        {

            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setString(3, cita.getEstado());
            stmt.setInt(4, cita.getIdMascota());
            stmt.setInt(5, cita.getIdVeterinario());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al guardar la cita",e);
        }
    }

    /**
     * Obtiene todas las citas registradas.
     *
     * @return Lista de citas
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public List<Cita> obtenerTodos() throws PersistenceException{
        List<Cita> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("cita.select.all");

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {

            while (rs.next()) {
                Cita cita = new Cita();
                cita.setIdCita(rs.getInt("idCita"));
                cita.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(rs.getString("estado"));
                cita.setIdMascota(rs.getInt("idMascota"));
                cita.setIdVeterinario(rs.getInt("idVeterinario"));
                lista.add(cita);
            }

        }
        catch (SQLException e) {
            throw new PersistenceException("Error al obtener todas las citas", e);
        }
        catch (ClassNotFoundException c) {
            throw new PersistenceException("Error al encontrar la clase", c);
        }


        return lista;
    }

    /**
     * Busca una cita por su ID.
     *
     * @param id El ID de la cita a buscar
     * @return La cita encontrada o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */

    @Override
    public Cita buscarPorId(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("cita.select.byId"); Cita cita = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1, id);

            if (rs.next()) {
                cita = new Cita();
                cita.setIdCita(rs.getInt("idCita"));
                cita.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(rs.getString("estado"));
                cita.setIdMascota(rs.getInt("idMascota"));
                cita.setIdVeterinario(rs.getInt("idVeterinario"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar cita por ID: " + id, e);
        }
        return cita;
    }

    /**
     * Actualiza los datos de una cita existente.
     *
     * @param cita La cita con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void actualizar(Cita cita) throws PersistenceException {
        String sql = QueryManager.getQuery("cita.update");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setString(3, cita.getEstado());
            stmt.setInt(4, cita.getIdMascota());
            stmt.setInt(5, cita.getIdVeterinario());
            stmt.setInt(6, cita.getIdCita());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al actualizar la cita", e);
        }
    }

    /**
     * Elimina una cita de la base de datos.
     *
     * @param id El ID de la cita a eliminar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void eliminar(int id) throws PersistenceException {
        String sql = QueryManager.getQuery("cita.delete");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al eliminar la cita con ID: " + id, e);
        }
    }
}