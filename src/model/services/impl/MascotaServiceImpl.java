package model.services.impl;

import model.dao.MascotaDAO;
import model.entities.Mascota;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.MascotaService;

import java.util.List;

/**
 * Implementación del servicio para gestión de mascotas en la veterinaria.
 * Proporciona operaciones CRUD para mascotas con validaciones de negocio.
 *
 * @author Luis Aguirre
 * @version 1.0
 * @since 2023
 */
public class MascotaServiceImpl implements MascotaService {

    private final MascotaDAO mascotaDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de mascotas.
     *
     * @param mascotaDAO el DAO que manejará las operaciones de persistencia
     */
    public MascotaServiceImpl (MascotaDAO mascotaDAO) {
        this.mascotaDAO = mascotaDAO;
    }

    /**
     * Guarda una nueva mascota después de validar sus datos.
     *
     * @param mascota la mascota a guardar
     * @throws BusinessException si la mascota no cumple validaciones o hay error de persistencia
     */
    @Override
    public void guardarMascota(Mascota mascota) throws BusinessException {
        validarMascota(mascota);
        try{
            mascotaDAO.guardar(mascota);
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al guardar la mascota: " + e.getMessage());
        }

    }

    /**
     * Obtiene todas las mascotas registradas en el sistema.
     *
     * @return lista de todas las mascotas
     * @throws BusinessException si ocurre un error al acceder a los datos
     */
    @Override
    public List<Mascota> obtenerTodosLasMascotas() throws BusinessException{
        try{
            return mascotaDAO.obtenerTodos();
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al obtener mascotas: " + e.getMessage());
        }
    }

    /**
     * Busca una mascota por su ID en la base de datos.
     *
     * @param id El ID de la mascota a buscar
     * @return La mascota encontrada
     * @throws BusinessException Si la mascota no existe o hay error de persistencia
     */
    @Override
    public Mascota buscarMascotaPorId(int id) throws BusinessException {
        try {
            return mascotaDAO.buscarPorId(id);
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al buscar cita: " + e.getMessage());
        }

    }

    /**
     * Actualiza los datos de una mascota existente.
     *
     * @param mascota la mascota con los datos actualizados
     * @throws BusinessException si la mascota no es válida o hay error de persistencia
     */
    @Override
    public void actualizarMascota(Mascota mascota) throws BusinessException {
        validarMascota(mascota);
        try{
            mascotaDAO.actualizar(mascota);
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al actualizar mascota: " + e.getMessage());
        }

    }

    /**
     * Elimina una mascota existente.
     *
     * @param id El ID de la mascota a eliminar
     * @throws BusinessException Si falla la operación en la base de datos
     */
    @Override
    public void eliminarMascota(int id) throws BusinessException {
        try{
            mascotaDAO.eliminar(id);
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al eliminar cita: " + e.getMessage());
        }
    }

    /**
     * Valida que los datos básicos de una mascota sean correctos.
     *
     * @param mascota la mascota a validar
     * @throws BusinessException si la mascota no cumple con las validaciones requeridas
     */
    private void validarMascota(Mascota mascota) throws BusinessException {
        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            throw new BusinessException("El nombre de la mascota es requerido");
        }
        if (mascota.getIdCliente() <= 0) {
            throw new BusinessException("Se debe asignar un cliente válido");
        }
    }
}
