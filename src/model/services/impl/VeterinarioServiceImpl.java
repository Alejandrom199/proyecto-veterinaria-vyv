package model.services.impl;

import model.dao.VeterinarioDAO;
import model.entities.Veterinario;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.VeterinarioService;

import java.util.List;

/**
 * Implementación del servicio para gestión de veterinarios.
 * Proporciona operaciones CRUD para veterinarios con validaciones básicas.
 *
 * @author Lenny Borbor
 * @version 1.0
 * @since 2023
 */
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioDAO veterinarioDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de veterinarios.
     *
     * @param veterinarioDAO el DAO que manejará las operaciones de persistencia
     */
    public VeterinarioServiceImpl(VeterinarioDAO veterinarioDAO) {
        this.veterinarioDAO = veterinarioDAO;
    }

    /**
     * Guarda un nuevo veterinario después de validar sus datos.
     *
     * @param veterinario el veterinario a guardar
     * @throws BusinessException si el veterinario no cumple validaciones o hay error de persistencia
     */
    @Override
    public void guardarVeterinario(Veterinario veterinario) throws BusinessException {
        validarVeterinario(veterinario);
        try{
            veterinarioDAO.guardar(veterinario);
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al guardar el veterinario: " + e.getMessage());
        }

    }

    /**
     * Obtiene todos los veterinarios registrados en el sistema.
     *
     * @return lista de todos los veterinarios
     * @throws BusinessException si ocurre un error al acceder a los datos
     */
    @Override
    public List<Veterinario> obtenerTodosLosVeterinarios() throws BusinessException{
        try {
            return veterinarioDAO.obtenerTodos();
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al obtener citas: " + e.getMessage());
        }
    }

    /**
     * Busca un veterinario por su ID en la base de datos.
     *
     * @param id El ID del veterinario a buscar
     * @return El veterinario encontrado
     * @throws BusinessException Si el veterinario no existe o hay error de persistencia
     */
    @Override
    public Veterinario buscarVeterinarioPorId(int id) throws BusinessException {
        try {
            return veterinarioDAO.buscarPorId(id);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al buscar veterinario: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un veterinario existente.
     *
     * @param veterinario el veterinario con los datos actualizados
     * @throws BusinessException si el veterinario no es válido o hay error de persistencia
     */
    @Override
    public void actualizarVeterinario(Veterinario veterinario) throws BusinessException {
        validarVeterinario(veterinario);
        try {
            veterinarioDAO.actualizar(veterinario);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al actualizar veterinario: " + e.getMessage());
        }
    }

    /**
     * Elimina un veterinario existente.
     *
     * @param id El ID del veterinario a eliminar
     * @throws BusinessException Si falla la operación en la base de datos
     */
    @Override
    public void eliminarVeterinario(int id) throws  BusinessException{
        try {
            veterinarioDAO.eliminar(id);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al eliminar veterinario: " + e.getMessage());
        }
    }

    /**
     * Valida que los datos básicos de un veterinario sean correctos.
     *
     * @param veterinario el veterinario a validar
     * @throws BusinessException si el veterinario no cumple con las validaciones requeridas
     */
    private void validarVeterinario(Veterinario veterinario) throws BusinessException {
        if (veterinario.getNombre() == null || veterinario.getNombre().trim().isEmpty()) {
            throw new BusinessException("El nombre del veterinario es requerido");
        }
    }
}
