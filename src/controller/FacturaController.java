package controller;

import model.entities.Factura;
import model.exceptions.BusinessException;
import model.services.FacturaService;
import model.services.impl.FacturaServiceImpl;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con facturas.
 * Gestiona la interacción entre la vista y los servicios de facturación.
 *
 * @author Anthony López
 * @version 1.0
 * @since 2023
 */
public class FacturaController {

    private final FacturaService facturaService;

    /**
     * Constructor que inicializa el controlador con un servicio de facturas.
     *
     * @param facturaService el servicio de facturas a utilizar
     */
    public FacturaController(FacturaService facturaService){
        this.facturaService = facturaService;
    }

    /**
     * Guarda una nueva factura en el sistema.
     *
     * @param factura la factura a guardar
     */
    public void guardarFactura(Factura factura) {
        try {
            facturaService.guardarFactura(factura);
        } catch (BusinessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las facturas registradas en el sistema.
     *
     * @return lista de todas las facturas
     */
    public List<Factura> obtenerTodasFacturas() {
        try {
            return facturaService.obtenerTodasLasFacturas();
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Busca una factura por su ID.
     *
     * @param id el ID de la factura a buscar
     * @return la factura encontrada o null si no existe
     */
    public Factura buscarFacturaPorId(int id) {
        try {
            return facturaService.buscarFacturaPorId(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de una factura existente.
     *
     * @param factura la factura con los datos actualizados
     */
    public void actualizarFactura(Factura factura) {
        try {
            facturaService.actualizarFactura(factura);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Elimina una factura del sistema.
     *
     * @param id el ID de la factura a eliminar
     */
    public void eliminarFactura(int id) {
        try {
            facturaService.eliminarFactura(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

