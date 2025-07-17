package model.services;

import model.entities.DetalleFactura;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de detalles de factura.
 * Proporciona métodos CRUD para los detalles de factura.
 *
 * @author Anthony López
 * @version 1.0
 */
public interface DetalleFacturaService {
    /**
     * Guarda un nuevo detalle de factura en el sistema.
     *
     * @param detalleFactura el objeto DetalleFactura a guardar
     * @throws BusinessException si ocurre un error durante el guardado
     */
    void guardarDetalleFactura(DetalleFactura detalleFactura) throws BusinessException;

    /**
     * Obtiene todos los detalles de factura registrados en el sistema.
     *
     * @return lista de todos los detalles de factura
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<DetalleFactura> obtenerTodosLosDetallesFactura() throws BusinessException;

    /**
     * Busca un detalle de factura por su ID.
     *
     * @param id el ID del detalle a buscar
     * @return el detalle de factura encontrado
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    DetalleFactura buscarDetalleFacturaPorId(int id) throws BusinessException;

    /**
     * Obtiene los detalles de factura asociados a una factura específica.
     *
     * @param idFactura el ID de la factura
     * @return lista de detalles de factura asociados
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<DetalleFactura> obtenerDetalleFacturaPorIdFactura(int idFactura) throws BusinessException;

    /**
     * Actualiza los datos de un detalle de factura existente.
     *
     * @param detalleFactura el objeto DetalleFactura con los datos actualizados
     * @throws BusinessException si ocurre un error durante la actualización
     */
    void actualizarDetalleFactura(DetalleFactura detalleFactura) throws BusinessException;

    /**
     * Elimina un detalle de factura del sistema.
     *
     * @param id el ID del detalle a eliminar
     * @throws BusinessException si ocurre un error durante la eliminación
     */
    void eliminarDetalleFactura(int id) throws BusinessException;
}