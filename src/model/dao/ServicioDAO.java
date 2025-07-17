package model.dao;

import model.database.Conexion;
import model.entities.Servicio;
import model.exceptions.PersistenceException;
import utils.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de servicios en la base de datos.
 * Implementa la interfaz GenericDAO para operaciones estándar.
 *
 * @author Mariana Yagual
 * @version 1.0
 * @see GenericDAO
 */
public class ServicioDAO implements GenericDAO<Servicio>{

    /**
     * Constructor por defecto.
     */
    public ServicioDAO() {}

    /**
     * Guarda un nuevo servicio en la base de datos.
     *
     * @param servicio El servicio a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void guardar(Servicio servicio) throws PersistenceException {
        String sql = QueryManager.getQuery("servicio.insert");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servicio.getNombreServicio());
            stmt.setString(2, servicio.getDescripcion());
            stmt.setDouble(3, servicio.getPrecio());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al guardar el servicio",e);
        }
    }

    /**
     * Obtiene todos los servicios registrados.
     *
     * @return Lista de servicios
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public List<Servicio> obtenerTodos() throws PersistenceException{
        List<Servicio> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("servicio.select.all");

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setIdServicio(rs.getInt("idServicio"));
                servicio.setNombreServicio(rs.getString("nombreServicio"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setPrecio(rs.getDouble("precio"));
                lista.add(servicio);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al obtener todas los servicios", e);
        }

        return lista;
    }

    /**
     * Busca un servicio por su ID.
     *
     * @param id El ID del servicio a buscar
     * @return El servicio encontrado o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public Servicio buscarPorId(int id) throws PersistenceException {
        String sql = QueryManager.getQuery("servicio.select.byId");
        Servicio servicio = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    servicio = new Servicio();
                    servicio.setIdServicio(rs.getInt("idServicio"));
                    servicio.setNombreServicio(rs.getString("nombreServicio"));
                    servicio.setDescripcion(rs.getString("descripcion"));
                    servicio.setPrecio(rs.getDouble("precio"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar servicio por ID: " + id, e);
        }

        return servicio;
    }

    /**
     * Actualiza los datos de un servicio existente.
     *
     * @param servicio El servicio con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void actualizar(Servicio servicio) throws PersistenceException{
        String sql = QueryManager.getQuery("servicio.update");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servicio.getNombreServicio());
            stmt.setString(2, servicio.getDescripcion());
            stmt.setDouble(3, servicio.getPrecio());
            stmt.setInt(4, servicio.getIdServicio());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al actualizar el servicio", e);
        }
    }

    /**
     * Elimina un servicio de la base de datos.
     *
     * @param id El ID del servicio a eliminar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void eliminar(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("servicio.delete");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al eliminar la cita con ID: " + id, e);
        }
    }
}

