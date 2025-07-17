package model.dao;

import model.exceptions.PersistenceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD en la base de datos.
 *
 * @param <T> Tipo de entidad a manejar
 * @author Saúl Maldonado
 * @version 1.0
 */
public interface GenericDAO<T> {

    /**
     * Guarda una entidad en la base de datos.
     *
     * @param entidad La entidad a guardar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    void guardar(T entidad) throws PersistenceException, SQLException, ClassNotFoundException;

    /**
     * Obtiene todas las entidades del tipo.
     *
     * @return Lista de entidades
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    List<T> obtenerTodos() throws PersistenceException;

    /**
     * Busca una entidad por su ID.
     *
     * @param id El ID de la entidad a buscar
     * @return La entidad encontrada o null si no existe
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    T buscarPorId(int id) throws PersistenceException;

    /**
     * Actualiza los datos de una entidad existente.
     *
     * @param entidad La entidad con los datos actualizados
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    void actualizar(T entidad) throws PersistenceException;

    /**
     * Elimina una entidad de la base de datos.
     *
     * @param id El ID de la entidad a eliminar
     * @throws PersistenceException Si ocurre un error en la base de datos
     */
    void eliminar(int id) throws PersistenceException;
}
