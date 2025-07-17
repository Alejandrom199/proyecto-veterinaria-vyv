package model.entities;

/**
 * Clase que representa el detalle de una factura en el sistema.
 * Contiene información sobre los servicios o medicamentos facturados.
 *
 * @author Saúl Maldonado
 * @version 1.0
 */
public class DetalleFactura {
    private int idDetalle;
    private int idFactura;
    private String tipo;
    private Integer idServicio; // puede ser null
    private Integer idMedicamento; // puede ser null
    private int cantidad;
    private double subtotal;


    /**
     * Constructor por defecto de DetalleFactura.
     */
    public DetalleFactura() {}

    /**
     * Constructor con parámetros para crear un detalle de factura.
     *
     * @param idFactura     ID de la factura asociada
     * @param tipo          Tipo de detalle (servicio/medicamento)
     * @param idServicio    ID del servicio (puede ser null)
     * @param idMedicamento ID del medicamento (puede ser null)
     * @param cantidad      Cantidad del ítem
     * @param subtotal      Subtotal calculado
     */
    public DetalleFactura(int idFactura, String tipo, Integer idServicio, Integer idMedicamento, int cantidad, double subtotal) {
        this.idFactura = idFactura;
        this.tipo = tipo;
        this.idServicio = idServicio;
        this.idMedicamento = idMedicamento;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    /**
     * Obtiene el ID del detalle.
     *
     * @return ID del detalle
     */
    public int getIdDetalle() {
        return idDetalle;
    }

    /**
     * Establece el ID del detalle.
     *
     * @param idDetalle ID del detalle a establecer
     */
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    /**
     * Obtiene el ID de la factura asociada.
     *
     * @return ID de la factura
     */
    public int getIdFactura() {
        return idFactura;
    }

    /**
     * Establece el ID de la factura asociada.
     *
     * @param idFactura ID de la factura a establecer
     */
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * Obtiene el tipo de detalle.
     *
     * @return Tipo de detalle (servicio/medicamento)
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de detalle.
     *
     * @param tipo Tipo de detalle a establecer
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el ID del servicio.
     *
     * @return ID del servicio (puede ser null)
     */
    public Integer getIdServicio() {
        return idServicio;
    }

    /**
     * Sets id servicio.
     *
     * @param idServicio the id servicio
     */
    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * Obtiene el ID del medicamento.
     *
     * @return ID del medicamento (puede ser null)
     */
    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    /**
     * Establece el ID del medicamento.
     *
     * @param idMedicamento ID del medicamento a establecer (puede ser null)
     */
    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    /**
     * Obtiene la cantidad del ítem.
     *
     * @return Cantidad del ítem
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del ítem.
     *
     * @param cantidad Cantidad a establecer
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el subtotal calculado.
     *
     * @return Valor del subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Establece el subtotal calculado.
     *
     * @param subtotal Valor del subtotal a establecer
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
