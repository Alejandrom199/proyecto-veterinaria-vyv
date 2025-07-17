package model.services;

import model.entities.Cliente;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de clientes.
 * Proporciona métodos CRUD para los clientes de la veterinaria.
 *
 * @author Andy Romero
 * @version 1.0
 */
public interface ClienteService {
    /**
     * Guarda un nuevo cliente en el sistema.
     *
     * @param cliente el objeto Cliente a guardar
     * @throws BusinessException si ocurre un error durante el guardado
     */
    void guardarCliente(Cliente cliente) throws BusinessException;

    /**
     * Obtiene todos los clientes registrados en el sistema.
     *
     * @return lista de todos los clientes
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<Cliente> obtenerTodosLosClientes() throws BusinessException;

    /**
     * Busca un cliente por su ID.
     *
     * @param id el ID del cliente a buscar
     * @return el cliente encontrado
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    Cliente buscarClientePorId(int id) throws BusinessException;

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param cliente el objeto Cliente con los datos actualizados
     * @throws BusinessException si ocurre un error durante la actualización
     */
    void actualizarCliente(Cliente cliente) throws BusinessException;

    /**
     * Elimina un cliente del sistema.
     *
     * @param id el ID del cliente a eliminar
     * @throws BusinessException si ocurre un error durante la eliminación
     */
    void eliminarCliente(int id) throws BusinessException;
}