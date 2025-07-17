package controller;

import model.dao.MascotaDAO;
import model.entities.Mascota;
import model.exceptions.BusinessException;
import model.services.MascotaService;
import model.services.impl.MascotaServiceImpl;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con mascotas.
 * Gestiona la interacción entre la vista y los servicios de mascotas.
 *
 * @author Luis Aguirre
 * @version 1.0
 * @since 2023
 */
public class MascotaController {

    private final MascotaService mascotaService;

    /**
     * Constructor que inicializa el controlador con un servicio de mascotas.
     *
     * @param mascotaService el servicio de mascotas a utilizar
     */
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    /**
     * Guarda una nueva mascota en el sistema.
     *
     * @param mascota la mascota a guardar
     */
    public void guardarMascota(Mascota mascota) {
        try{
            mascotaService.guardarMascota(mascota);
        }
        catch (BusinessException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    /**
     * Obtiene todas las mascotas registradas en el sistema.
     *
     * @return lista de todas las mascotas
     */
    public List<Mascota> obtenerTodasMascotas() {
        try{
            return mascotaService.obtenerTodosLasMascotas();
        }
        catch (BusinessException e){
            System.err.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Busca una mascota por su ID.
     *
     * @param id el ID de la mascota a buscar
     * @return la mascota encontrada o null si no existe
     */
    public Mascota buscarMascotaPorId(int id) {
        try {
            return mascotaService.buscarMascotaPorId(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de una mascota existente.
     *
     * @param mascota la mascota con los datos actualizados
     */
    public void actualizarMascota(Mascota mascota) {
        try {
            mascotaService.actualizarMascota(mascota);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Elimina una mascota del sistema.
     *
     * @param id el ID de la mascota a eliminar
     */
    public void eliminarMascota(int id) {
        try {
            mascotaService.eliminarMascota(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}