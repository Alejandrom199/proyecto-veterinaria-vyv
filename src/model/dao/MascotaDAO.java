package model.dao;

import model.database.Conexion;
import model.entities.Cliente;
import model.entities.Mascota;
import model.exceptions.PersistenceException;
import utils.AppFactory;
import utils.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de mascotas en la base de datos.
 * Implementa la interfaz GenericDAO para operaciones estándar.
 *
 * @author Luis Aguirre
 * @version 1.0
 * @see GenericDAO
 */
public class MascotaDAO implements GenericDAO<Mascota>{

    /**
     * Constructor por defecto.
     */
    public MascotaDAO() {}

    /**
     * Guarda una nueva mascota en la base de datos.
     *
     * @param mascota La mascota a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void guardar(Mascota mascota) throws PersistenceException {
        String sql = QueryManager.getQuery("mascota.insert");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setString(5, mascota.getSexo());
            stmt.setInt(6, mascota.getIdCliente());

            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al guardar la mascota",e);
        }
    }

    /**
     * Obtiene todas las mascotas registradas.
     *
     * @return Lista de mascotas
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public List<Mascota> obtenerTodos() throws PersistenceException{
        List<Mascota> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("mascota.select.all");

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mascota mascota = new Mascota();
                mascota.setIdMascota(rs.getInt("idMascota"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setSexo(rs.getString("sexo"));
                mascota.setIdCliente(rs.getInt("idCliente"));
                lista.add(mascota);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al obtener todas las mascotas", e);
        }

        return lista;
    }

    /**
     * Busca una mascota por su ID.
     *
     * @param id El ID de la mascota a buscar
     * @return La mascota encontrada o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public Mascota buscarPorId(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("mascota.select.byId");
        Mascota mascota = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mascota = new Mascota();
                    mascota.setIdMascota(rs.getInt("idMascota"));
                    mascota.setNombre(rs.getString("nombre"));
                    mascota.setEspecie(rs.getString("especie"));
                    mascota.setRaza(rs.getString("raza"));
                    mascota.setEdad(rs.getInt("edad"));
                    mascota.setSexo(rs.getString("sexo"));
                    mascota.setIdCliente(rs.getInt("idCliente"));

                    // Aquí cargamos el Cliente asociado con el idCliente
                    Cliente cliente = AppFactory.getClienteDAO().buscarPorId(mascota.getIdCliente());
                    mascota.setCliente(cliente);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar mascota por ID: " + id, e);
        }

        return mascota;
    }

    /**
     * Actualiza los datos de una mascota existente.
     *
     * @param mascota La mascota con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void actualizar(Mascota mascota) throws PersistenceException{
        String sql = QueryManager.getQuery("mascota.update");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setString(5, mascota.getSexo());
            stmt.setInt(6, mascota.getIdCliente());
            stmt.setInt(7, mascota.getIdMascota());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al actualizar la mascota", e);
        }
    }

    /**
     * Elimina una mascota de la base de datos.
     *
     * @param id El ID de la mascota a eliminar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void eliminar(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("mascota.delete");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al eliminar la cita con ID: " + id, e);
        }
    }
}
