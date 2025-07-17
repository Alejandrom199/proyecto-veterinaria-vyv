package controller;

import model.entities.DetalleFactura;
import model.exceptions.BusinessException;
import model.services.DetalleFacturaService;
import model.services.impl.DetalleFacturaServiceImpl;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con detalles de factura.
 * Gestiona la interacción entre la vista y los servicios de detalles de factura.
 *
 * @author Anthony López
 * @version 1.0
 * @since 2023
 */
public class DetalleFacturaController {

    private final DetalleFacturaService detalleFacturaService;

    /**
     * Constructor que inicializa el controlador con un servicio de detalles de factura.
     *
     * @param detalleFacturaService el servicio de detalles de factura a utilizar
     */
    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    /**
     * Guarda un nuevo detalle de factura en el sistema.
     *
     * @param detalleFactura el detalle de factura a guardar
     */
    public void guardarDetalleFactura(DetalleFactura detalleFactura) {
        try{
            detalleFacturaService.guardarDetalleFactura(detalleFactura);
        }
        catch (BusinessException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los detalles de factura registrados en el sistema.
     *
     * @return lista de todos los detalles de factura
     */
    public List<DetalleFactura> obtenerTodosDetallesFactura() {
        try{
            return detalleFacturaService.obtenerTodosLosDetallesFactura();
        }
        catch (BusinessException e){
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Busca un detalle de factura por su ID.
     *
     * @param id el ID del detalle a buscar
     * @return el detalle encontrado o null si no existe
     */
    public DetalleFactura buscarDetalleFacturaPorId(int id) {
        try{
            return detalleFacturaService.buscarDetalleFacturaPorId(id);
        }
        catch (BusinessException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene los detalles de factura asociados a una factura específica.
     *
     * @param idFactura el ID de la factura
     * @return lista de detalles de factura asociados
     */
    public List<DetalleFactura> obtenerDetallesPorFactura(int idFactura) {
        try{
            return detalleFacturaService.obtenerDetalleFacturaPorIdFactura(idFactura);
        }
        catch (BusinessException e){
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Actualiza los datos de un detalle de factura existente.
     *
     * @param detalleFactura el detalle con los datos actualizados
     */
    public void actualizarDetalleFactura(DetalleFactura detalleFactura) {
        try{
            detalleFacturaService.actualizarDetalleFactura(detalleFactura);
        }
        catch (BusinessException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Elimina un detalle de factura del sistema.
     *
     * @param id el ID del detalle a eliminar
     */
    public void eliminarDetalleFactura(int id) {
        try{
            detalleFacturaService.eliminarDetalleFactura(id);
        }
        catch (BusinessException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

