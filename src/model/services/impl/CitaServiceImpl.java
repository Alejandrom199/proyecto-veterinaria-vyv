package model.services.impl;

import model.dao.CitaDAO;
import model.entities.Cita;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.CitaService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación del servicio para gestión de citas médicas veterinarias.
 * Proporciona operaciones CRUD para citas con validaciones de negocio.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023-10-01
 */
public class CitaServiceImpl implements CitaService {

    private final CitaDAO citaDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de citas.
     *
     * @param citaDAO el DAO que manejará las operaciones de persistencia
     */
    public CitaServiceImpl(CitaDAO citaDAO) {
        this.citaDAO = citaDAO;
    }

    /**
     * Guarda una nueva cita después de validar sus datos.
     *
     * @param cita la cita a guardar
     * @throws BusinessException si la cita no cumple las validaciones o hay error de persistencia
     * @see #validarCita(Cita)
     */
    @Override
    public void guardarCita(Cita cita) throws BusinessException {
        validarCita(cita);
        try {
            citaDAO.guardar(cita);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al guardar la cita: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las citas registradas en el sistema.
     *
     * @return lista de todas las citas
     * @throws BusinessException si ocurre un error al acceder a los datos
     */
    @Override
    public List<Cita> obtenerTodasCitas() throws BusinessException {
        try {
            return citaDAO.obtenerTodos();
        } catch (PersistenceException e) {
            throw new BusinessException("Error al obtener citas: " + e.getMessage());
        }
    }

    /**
     * Busca una cita por su ID en la base de datos.
     *
     * @param id El ID de la cita a buscar (debe ser mayor que 0)
     * @return La cita encontrada
     * @throws BusinessException Si la cita no existe o hay un error de persistencia
     * @see CitaDAO#buscarPorId(int)
     */
    @Override
    public Cita buscarCitaPorId(int id) throws BusinessException {
        try {
            Cita cita = citaDAO.buscarPorId(id);

            if(cita == null){
                throw new BusinessException("No se encontró una cita con el ID proporcionado");
            }

            return cita;
        } catch (PersistenceException e) {
            throw new BusinessException("Error al buscar cita: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de una cita existente.
     *
     * @param cita la cita con los datos actualizados
     * @throws BusinessException si la cita no es válida o hay error de persistencia
     */
    @Override
    public void actualizarCita(Cita cita) throws BusinessException {
        if (cita.getMotivo() == null || cita.getMotivo().trim().isEmpty()) {
            throw new BusinessException("El motivo es requerido");
        }

        try {
            citaDAO.actualizar(cita);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al actualizar cita: " + e.getMessage());
        }
    }

    /**
     * Elimina una cita existente después de validar su existencia.
     *
     * @param id El ID de la cita a eliminar
     * @throws BusinessException Si la cita no existe o falla la operación en la BD
     */
    @Override
    public void eliminarCita(int id) throws BusinessException {
        try {
            Cita citaExistente = citaDAO.buscarPorId(id);

            if (citaExistente == null){
                throw new BusinessException("No existe una cita con el ID proporcionado");
            }

            citaDAO.eliminar(id);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al eliminar cita: " + e.getMessage());
        }
    }

    /**
     * Valida que los datos de una cita sean correctos según las reglas de negocio.
     *
     * @param cita la cita a validar
     * @throws BusinessException si la cita no cumple con las validaciones requeridas
     */
    private void validarCita(Cita cita) throws BusinessException {
        if (!cita.getFechaHora().isBefore(LocalDateTime.now().plusMinutes(1))) {
            throw new BusinessException("La fecha/hora no puede estar en el pasado");
        }

        if (cita.getMotivo() == null || cita.getMotivo().trim().isEmpty()) {
            throw new BusinessException("El motivo es requerido");
        }

        if (cita.getMotivo().trim().length() < 5) {
            throw new BusinessException("El motivo debe tener al menos 5 caracteres");
        }

        if (cita.getEstado() == null || cita.getEstado().trim().isEmpty()) {
            throw new BusinessException("El estado es requerido");
        }

        String estado = cita.getEstado().trim().toLowerCase();
        if (!estado.equals("pendiente") && !estado.equals("confirmada") && !estado.equals("cancelada")) {
            throw new BusinessException("El estado debe ser 'Pendiente', 'Confirmada' o 'Cancelada'");
        }

        if (cita.getIdMascota() <= 0) {
            throw new BusinessException("El ID de la mascota debe ser positivo");
        }

        if (cita.getIdVeterinario() <= 0) {
            throw new BusinessException("El ID del veterinario debe ser positivo");
        }
    }
}