package model.services;

import model.entities.Factura;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de facturas.
 * Proporciona métodos CRUD para las facturas del sistema.
 *
 * @author Anthony López
 * @version 1.0
 */
public interface FacturaService {
    /**
     * Guarda una nueva factura en el sistema.
     *
     * @param factura el objeto Factura a guardar
     * @throws BusinessException si ocurre un error durante el guardado
     */
    void guardarFactura(Factura factura) throws BusinessException;

    /**
     * Obtiene todas las facturas registradas en el sistema.
     *
     * @return lista de todas las facturas
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<Factura> obtenerTodasLasFacturas() throws BusinessException;

    /**
     * Busca una factura por su ID.
     *
     * @param id el ID de la factura a buscar
     * @return la factura encontrada
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    Factura buscarFacturaPorId(int id) throws BusinessException;

    /**
     * Actualiza los datos de una factura existente.
     *
     * @param factura el objeto Factura con los datos actualizados
     * @throws BusinessException si ocurre un error durante la actualización
     */
    void actualizarFactura(Factura factura) throws BusinessException;

    /**
     * Elimina una factura del sistema.
     *
     * @param id el ID de la factura a eliminar
     * @throws BusinessException si ocurre un error durante la eliminación
     */
    void eliminarFactura(int id) throws BusinessException;
}