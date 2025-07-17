package model.dao;

import model.database.Conexion;
import model.entities.Cliente;
import model.exceptions.PersistenceException;
import utils.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de clientes en la base de datos.
 * Implementa la interfaz GenericDAO para operaciones estándar.
 *
 * @author Andy Romero
 * @version 1.0
 * @see GenericDAO
 */
public class ClienteDAO implements GenericDAO<Cliente>{

    /**
     * Constructor por defecto.
     */
    public ClienteDAO(){}

    /**
     * Guarda un nuevo cliente en la base de datos.
     *
     * @param cliente El cliente a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */

    @Override
    public void guardar(Cliente cliente) throws PersistenceException {
        String sql = QueryManager.getQuery("cliente.insert");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al guardar el cliente",e);
        }
    }

    /**
     * Obtiene todos los clientes registrados.
     *
     * @return Lista de clientes
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public List<Cliente> obtenerTodos() throws PersistenceException{
        List<Cliente> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("cliente.select.all");

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getString("telefono"));
                lista.add(cliente);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al obtener todas los clientes", e);
        }

        return lista;
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id El ID del cliente a buscar
     * @return El cliente encontrado o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public Cliente buscarPorId(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("cliente.select.byId");
        Cliente cliente = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setTelefono(rs.getString("telefono"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar cliente por ID: " + id, e);
        }

        return cliente;
    }

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param cliente El cliente con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void actualizar(Cliente cliente) throws PersistenceException{
        String sql = QueryManager.getQuery("cliente.update");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setInt(3, cliente.getIdCliente());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al actualizar la cita", e);
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param id El ID del cliente a eliminar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void eliminar(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("cliente.delete");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al eliminar el cliente con ID: " + id, e);
        }
    }
}
