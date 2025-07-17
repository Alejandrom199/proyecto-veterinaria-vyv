package model.dao;

import model.database.Conexion;
import model.dto.MedicamentoMasVendidoDTO;
import model.entities.Medicamento;
import model.exceptions.PersistenceException;
import utils.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de medicamentos en la base de datos.
 * Implementa la interfaz GenericDAO para operaciones estándar.
 *
 * @author Jeremy Alvarado
 * @version 1.0
 * @see GenericDAO
 */
public class MedicamentoDAO implements GenericDAO<Medicamento>{

    /**
     * Constructor por defecto.
     */
    public MedicamentoDAO() {}

    /**
     * Guarda un nuevo medicamento en la base de datos.
     *
     * @param medicamento El medicamento a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void guardar(Medicamento medicamento) throws PersistenceException {
        String sql = QueryManager.getQuery("medicamento.insert");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medicamento.getNombre());
            stmt.setString(2, medicamento.getDescripcion());
            stmt.setDouble(3, medicamento.getPrecio());
            stmt.executeUpdate();

        }
        catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al guardar el medicamento",e);
        }
    }

    /**
     * Obtiene todos los medicamentos registrados.
     *
     * @return Lista de medicamentos
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public List<Medicamento> obtenerTodos() throws PersistenceException{
        List<Medicamento> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("medicamento.select.all");

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setIdMedicamento(rs.getInt("idMedicamento"));
                medicamento.setNombre(rs.getString("nombre"));
                medicamento.setDescripcion(rs.getString("descripcion"));
                medicamento.setPrecio(rs.getDouble("precio"));
                lista.add(medicamento);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al obtener todas los medicamentos", e);
        }

        return lista;
    }

    /**
     * Busca un medicamento por su ID.
     *
     * @param id El ID del medicamento a buscar
     * @return El medicamento encontrado o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public Medicamento buscarPorId(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("medicamento.select.byId");
        Medicamento medicamento = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    medicamento = new Medicamento();
                    medicamento.setIdMedicamento(rs.getInt("idMedicamento"));
                    medicamento.setNombre(rs.getString("nombre"));
                    medicamento.setDescripcion(rs.getString("descripcion"));
                    medicamento.setPrecio(rs.getDouble("precio"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar medicamento por ID: " + id, e);
        }

        return medicamento;
    }

    /**
     * Actualiza los datos de un medicamento existente.
     *
     * @param medicamento El medicamento con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void actualizar(Medicamento medicamento) throws PersistenceException{
        String sql = QueryManager.getQuery("medicamento.update");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medicamento.getNombre());
            stmt.setString(2, medicamento.getDescripcion());
            stmt.setDouble(3, medicamento.getPrecio());
            stmt.setInt(4, medicamento.getIdMedicamento());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al actualizar el medicamento", e);
        }
    }

    /**
     * Elimina un medicamento de la base de datos.
     *
     * @param id El ID del medicamento a eliminar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void eliminar(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("medicamento.delete");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al eliminar el medicamento con ID: " + id, e);
        }
    }

    /**
     * Obtiene los medicamentos más vendidos.
     *
     * @return Lista de DTOs con información de medicamentos más vendidos
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    public List<MedicamentoMasVendidoDTO> buscarMedicamentosMasVendidos() throws PersistenceException{
        List<MedicamentoMasVendidoDTO> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("medicamento.select.masVendidos");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int total = rs.getInt("total_vendido");
                lista.add(new MedicamentoMasVendidoDTO(nombre, total));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar medicamentos más vendidos: ", e);
        }

        System.out.println(lista);
        return lista;
    }

}