package model.dao;

import model.database.Conexion;
import model.entities.Factura;
import model.exceptions.PersistenceException;
import utils.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de facturas en la base de datos.
 * Implementa la interfaz GenericDAO para operaciones estándar.
 *
 * @author Anthony López
 * @version 1.0
 * @see GenericDAO
 */
public class FacturaDAO implements GenericDAO<Factura>{

    /**
     * Constructor por defecto.
     */
    public FacturaDAO(){}

    /**
     * Guarda una nueva factura en la base de datos.
     *
     * @param factura La factura a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void guardar(Factura factura) throws PersistenceException {
        String sql = QueryManager.getQuery("factura.insert");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(factura.getFechaEmision()));
            stmt.setDouble(2, factura.getTotal());
            stmt.setInt(3, factura.getIdCliente());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al guardar la factura",e);
        }
    }

    /**
     * Obtiene todas las facturas registradas.
     *
     * @return Lista de facturas
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public List<Factura> obtenerTodos() throws PersistenceException{
        List<Factura> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("factura.select.all");

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setFechaEmision(rs.getDate("fechaEmision").toLocalDate());
                factura.setTotal(rs.getDouble("total"));
                factura.setIdCliente(rs.getInt("idCliente"));
                lista.add(factura);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al obtener todas las facturas", e);
        }

        return lista;
    }

    /**
     * Busca una factura por su ID.
     *
     * @param id El ID de la factura a buscar
     * @return La factura encontrada o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public Factura buscarPorId(int id) throws PersistenceException {
        String sql = QueryManager.getQuery("factura.select.byId");
        Factura factura = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    factura = new Factura();
                    factura.setIdFactura(rs.getInt("idFactura"));
                    factura.setFechaEmision(rs.getDate("fechaEmision").toLocalDate());
                    factura.setTotal(rs.getDouble("total"));
                    factura.setIdCliente(rs.getInt("idCliente"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar factura por ID: " + id, e);
        }

        return factura;
    }

    /**
     * Actualiza los datos de una factura existente.
     *
     * @param factura La factura con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void actualizar(Factura factura) throws PersistenceException{
        String sql = QueryManager.getQuery("factura.update");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(factura.getFechaEmision()));
            stmt.setDouble(2, factura.getTotal());
            stmt.setInt(3, factura.getIdCliente());
            stmt.setInt(4, factura.getIdFactura());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al actualizar la factura", e);
        }
    }

    /**
     * Elimina una factura de la base de datos.
     *
     * @param id El ID de la factura a eliminar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void eliminar(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("factura.delete");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al eliminar la factura con ID: " + id, e);
        }
    }
}
