package controller;

import model.entities.Cliente;
import model.services.ClienteService;
import model.services.impl.ClienteServiceImpl;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con clientes.
 * Gestiona la interacción entre la vista y los servicios de clientes.
 *
 * @author Andy Romero
 * @version 1.0
 * @since 2023
 */
public class ClienteController {

    private final ClienteService clienteService;

    /**
     * Constructor que inicializa el controlador con un servicio de clientes.
     *
     * @param clienteService el servicio de clientes a utilizar
     */
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Guarda un nuevo cliente en el sistema.
     *
     * @param cliente el cliente a guardar
     */
    public void guardarCliente(Cliente cliente) {
        try {
            clienteService.guardarCliente(cliente);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los clientes registrados en el sistema.
     *
     * @return lista de todos los clientes
     */
    public List<Cliente> obtenerTodosClientes() {
        try{
            return clienteService.obtenerTodosLosClientes();
        }
        catch (BusinessException e){
            System.err.println("Error: " + e.getMessage());
        }
        return List.of();
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id el ID del cliente a buscar
     * @return el cliente encontrado o null si no existe
     */
    public Cliente buscarClientePorId(int id) {
        try{
            return clienteService.buscarClientePorId(id);
        }
        catch (BusinessException e){
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param cliente el cliente con los datos actualizados
     */
    public void actualizarCliente(Cliente cliente) {
        try {
            clienteService.actualizarCliente(cliente);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Elimina un cliente del sistema.
     *
     * @param id el ID del cliente a eliminar
     */
    public void eliminarCliente(int id) {
        try{
            clienteService.eliminarCliente(id);
        }
        catch (BusinessException e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}
