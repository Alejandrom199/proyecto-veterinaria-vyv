package model.dao;

import model.database.Conexion;
import model.entities.DetalleFactura;
import model.exceptions.PersistenceException;
import utils.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de detalles de factura en la base de datos.
 * Implementa la interfaz GenericDAO para operaciones estándar.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @see GenericDAO
 */
public class DetalleFacturaDAO implements GenericDAO<DetalleFactura>{

    /**
     * Constructor por defecto.
     */
    public DetalleFacturaDAO(){}

    /**
     * Guarda un nuevo detalle de factura en la base de datos.
     *
     * @param detalleFactura El detalle de factura a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void guardar(DetalleFactura detalleFactura) throws PersistenceException {
        String sql = QueryManager.getQuery("detalleFactura.insert");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalleFactura.getIdFactura());
            stmt.setString(2, detalleFactura.getTipo());
            stmt.setObject(3, detalleFactura.getIdServicio(), Types.INTEGER); // Servicio puede ser null
            stmt.setObject(4, detalleFactura.getIdMedicamento(), Types.INTEGER); // Producto puede ser null
            stmt.setInt(5, detalleFactura.getCantidad());
            stmt.setDouble(6, detalleFactura.getSubtotal());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al guardar el detalle factura",e);
        }
    }

    /**
     * Obtiene todos los detalles de factura registrados.
     *
     * @return Lista de detalles de factura
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public List<DetalleFactura> obtenerTodos() throws PersistenceException{
        List<DetalleFactura> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("detalleFactura.select.all");

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DetalleFactura detalleFactura = new DetalleFactura();
                detalleFactura.setIdDetalle(rs.getInt("idDetalle"));
                detalleFactura.setIdFactura(rs.getInt("idFactura"));
                detalleFactura.setTipo(rs.getString("tipo"));
                detalleFactura.setIdServicio(rs.getInt("idServicio"));
                detalleFactura.setIdMedicamento(rs.getInt("idProducto"));
                detalleFactura.setCantidad(rs.getInt("cantidad"));
                detalleFactura.setSubtotal(rs.getDouble("subtotal"));
                lista.add(detalleFactura);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al obtener todos los detalles factura", e);
        }

        return lista;
    }

    /**
     * Busca un detalle de factura por su ID.
     *
     * @param id El ID del detalle a buscar
     * @return El detalle encontrado o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public DetalleFactura buscarPorId(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("detalleFactura.select.byId");
        DetalleFactura detalleFactura = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    detalleFactura = new DetalleFactura();
                    detalleFactura.setIdDetalle(rs.getInt("idDetalle"));
                    detalleFactura.setIdFactura(rs.getInt("idFactura"));
                    detalleFactura.setTipo(rs.getString("tipo"));
                    detalleFactura.setIdServicio(rs.getInt("idServicio"));
                    detalleFactura.setIdMedicamento(rs.getInt("idProducto"));
                    detalleFactura.setCantidad(rs.getInt("cantidad"));
                    detalleFactura.setSubtotal(rs.getDouble("subtotal"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar detalle factura por ID: " + id, e);
        }

        return detalleFactura;
    }

    /**
     * Obtiene todos los detalles de factura asociados a una factura específica.
     *
     * @param idFactura El ID de la factura a buscar
     * @return Lista de detalles de factura asociados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    public List<DetalleFactura> obtenerPorIdFactura(int idFactura) throws PersistenceException{
        List<DetalleFactura> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("detalleFactura.select.ByIdFactura");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFactura);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DetalleFactura detalleFactura = new DetalleFactura();
                    detalleFactura.setIdDetalle(rs.getInt("idDetalle"));
                    detalleFactura.setIdFactura(rs.getInt("idFactura"));
                    detalleFactura.setTipo(rs.getString("tipo"));
                    detalleFactura.setIdServicio(rs.getInt("idServicio"));
                    detalleFactura.setIdMedicamento(rs.getInt("idProducto"));
                    detalleFactura.setCantidad(rs.getInt("cantidad"));
                    detalleFactura.setSubtotal(rs.getDouble("subtotal"));
                    lista.add(detalleFactura);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar el id factura: " + idFactura, e);
        }

        return lista;
    }

    /**
     * Actualiza los datos de un detalle de factura existente.
     *
     * @param detalleFactura El detalle con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void actualizar(DetalleFactura detalleFactura) throws PersistenceException{
        String sql = QueryManager.getQuery("detalleFactura.update");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalleFactura.getIdFactura());
            stmt.setString(2, detalleFactura.getTipo());
            stmt.setObject(3, detalleFactura.getIdServicio(), Types.INTEGER); // Servicio puede ser null
            stmt.setObject(4, detalleFactura.getIdMedicamento(), Types.INTEGER); // Producto puede ser null
            stmt.setInt(5, detalleFactura.getCantidad());
            stmt.setDouble(6, detalleFactura.getSubtotal());
            stmt.setInt(7, detalleFactura.getIdDetalle());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al actualizar el detalle factura", e);
        }
    }

    /**
     * Elimina un detalle de factura de la base de datos.
     *
     * @param id El ID del detalle a eliminar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void eliminar(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("detalleFactura.delete");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al eliminar el detalle factura con ID: " + id, e);
        }
    }
}

