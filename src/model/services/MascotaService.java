package model.services;

import model.entities.Mascota;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de mascotas.
 * Proporciona métodos CRUD para las mascotas de los clientes.
 *
 * @author Luis Aguirre
 * @version 1.0
 */
public interface MascotaService {
    /**
     * Guarda una nueva mascota en el sistema.
     *
     * @param mascota el objeto Mascota a guardar
     * @throws BusinessException si ocurre un error durante el guardado
     */
    void guardarMascota(Mascota mascota) throws BusinessException;

    /**
     * Obtiene todas las mascotas registradas en el sistema.
     *
     * @return lista de todas las mascotas
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<Mascota> obtenerTodosLasMascotas() throws BusinessException;

    /**
     * Busca una mascota por su ID.
     *
     * @param id el ID de la mascota a buscar
     * @return la mascota encontrada
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    Mascota buscarMascotaPorId(int id) throws BusinessException;

    /**
     * Actualiza los datos de una mascota existente.
     *
     * @param mascota el objeto Mascota con los datos actualizados
     * @throws BusinessException si ocurre un error durante la actualización
     */
    void actualizarMascota(Mascota mascota) throws BusinessException;

    /**
     * Elimina una mascota del sistema.
     *
     * @param id el ID de la mascota a eliminar
     * @throws BusinessException si ocurre un error durante la eliminación
     */
    void eliminarMascota(int id) throws BusinessException;
}