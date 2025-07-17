package model.services.impl;

import model.dao.ClienteDAO;
import model.entities.Cliente;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.ClienteService;

import java.util.List;

/**
 * Implementación del servicio para gestión de clientes de la veterinaria.
 * Proporciona operaciones CRUD para clientes con validaciones básicas.
 *
 * @author Andy Romero
 * @version 1.0
 * @since 2023
 */
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDAO clienteDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de clientes.
     *
     * @param clienteDAO el DAO que manejará las operaciones de persistencia
     */
    public ClienteServiceImpl(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    /**
     * Guarda un nuevo cliente después de validar sus datos.
     *
     * @param cliente el cliente a guardar
     * @throws BusinessException si el cliente no cumple validaciones o hay error de persistencia
     */
    @Override
    public void guardarCliente(Cliente cliente) throws BusinessException {
        validarCliente(cliente);
        try {
            clienteDAO.guardar(cliente);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al guardar la cliente: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los clientes registrados en el sistema.
     *
     * @return lista de todos los clientes
     * @throws BusinessException si ocurre un error al acceder a los datos
     */
    @Override
    public List<Cliente> obtenerTodosLosClientes() throws BusinessException{
        try{
            return clienteDAO.obtenerTodos();
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al obtener clientes: " + e.getMessage());
        }
    }

    /**
     * Busca un cliente por su ID en la base de datos.
     *
     * @param id El ID del cliente a buscar
     * @return El cliente encontrado
     * @throws BusinessException Si el cliente no existe o hay error de persistencia
     */
    @Override
    public Cliente buscarClientePorId(int id) throws BusinessException{
        try{
            return clienteDAO.buscarPorId(id);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al buscar cliente: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param cliente el cliente con los datos actualizados
     * @throws BusinessException si el cliente no es válido o hay error de persistencia
     */
    @Override
    public void actualizarCliente(Cliente cliente) throws BusinessException {
        validarCliente(cliente);
        try {
            clienteDAO.actualizar(cliente);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al actualizar cliente: " + e.getMessage());
        }
    }

    /**
     * Elimina un cliente existente.
     *
     * @param id El ID del cliente a eliminar
     * @throws BusinessException Si falla la operación en la base de datos
     */
    @Override
    public void eliminarCliente(int id) throws BusinessException {
        try{
            clienteDAO.eliminar(id);
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al eliminar cliente: " + e.getMessage());
        }
    }

    /**
     * Valida que los datos básicos de un cliente sean correctos.
     *
     * @param cliente el cliente a validar
     * @throws BusinessException si el cliente no cumple con las validaciones requeridas
     */
    private void validarCliente(Cliente cliente) throws BusinessException {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new BusinessException("El nombre del cliente es requerido");
        }
    }
}