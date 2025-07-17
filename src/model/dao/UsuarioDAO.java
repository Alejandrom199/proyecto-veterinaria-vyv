package model.dao;

import model.database.Conexion;
import model.entities.Usuario;
import model.exceptions.PersistenceException;
import utils.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de usuarios en la base de datos.
 * Implementa la interfaz GenericDAO para operaciones estándar.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @see GenericDAO
 */
public class UsuarioDAO implements GenericDAO<Usuario> {

    /**
     * Constructor por defecto.
     */
    public UsuarioDAO() {}

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param usuario El usuario a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void guardar(Usuario usuario) throws PersistenceException {
        String sql = QueryManager.getQuery("usuario.insert");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContrasena());
            stmt.setString(3, usuario.getRol());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new PersistenceException("Error al guardar el usuario",e);
        }
    }

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return Lista de usuarios
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public List<Usuario> obtenerTodos() throws PersistenceException{
        List<Usuario> lista = new ArrayList<>();
        String sql = QueryManager.getQuery("usuario.select.all");

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                lista.add(usuario);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al obtener todas los usuarios", e);
        }

        return lista;
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id El ID del usuario a buscar
     * @return El usuario encontrado o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public Usuario buscarPorId(int id) throws PersistenceException{
        String sql = QueryManager.getQuery("usuario.select.byId");
        Usuario usuario = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setRol(rs.getString("rol"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar usuario por ID: " + id, e);
        }

        return usuario;
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario a buscar
     * @return El usuario encontrado o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    public Usuario buscarPorNombreUsuario(String nombreUsuario) throws PersistenceException{
        String sql = QueryManager.getQuery("usuario.select.porNombreUsuario");
        Usuario usuario = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);  // Usamos setString porque nombreUsuario es un String
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setRol(rs.getString("rol"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al buscar usuario por nombre de usuario: " + nombreUsuario, e);
        }

        return usuario;
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario El usuario con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    @Override
    public void actualizar(Usuario usuario) throws PersistenceException{
        String sql = QueryManager.getQuery("usuario.update");

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContrasena());
            stmt.setString(3, usuario.getRol());
            stmt.setInt(4, usuario.getIdUsuario());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException("Error al actualizar el usuario", e);
        }
    }

    /**
     * Elimina un usuario de la base de datos.
     * (Actualmente no implementado por requisitos del sistema)
     *
     * @param id El ID del usuario a eliminar
     * @throws PersistenceException Siempre lanza una excepción ya que no está implementado
     */
    @Override
    public void eliminar(int id) throws PersistenceException {
        throw new PersistenceException("Método no implementado");
    }


}
