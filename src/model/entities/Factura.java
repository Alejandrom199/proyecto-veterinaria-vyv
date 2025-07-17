package model.entities;

import java.time.LocalDate;

/**
 * Clase que representa una factura en el sistema veterinario.
 * Contiene información sobre pagos y servicios realizados.
 *
 * @author Anthony López
 * @version 1.0
 */
public class Factura {
    private int idFactura;
    private LocalDate fechaEmision;
    private double total;
    private int idCliente; // FK
    private int idCita;

    /**
     * Constructor por defecto de Factura.
     */
    public Factura() {}

    /**
     * Constructor con parámetros básicos para crear una factura.
     *
     * @param fechaEmision Fecha de emisión de la factura
     * @param total        Monto total de la factura
     * @param idCliente    ID del cliente asociado
     * @param idCita       ID de la cita asociada
     */
    public Factura(LocalDate fechaEmision, double total, int idCliente, int idCita) {
        this.fechaEmision = fechaEmision;
        this.total = total;
        this.idCliente = idCliente;
        this.idCita = idCita;
    }

    /**
     * Constructor completo para crear una factura.
     *
     * @param idFactura    ID único de la factura
     * @param fechaEmision Fecha de emisión de la factura
     * @param total        Monto total de la factura
     * @param idCliente    ID del cliente asociado
     * @param idCita       ID de la cita asociada
     */
    public Factura(int idFactura, LocalDate fechaEmision, double total, int idCliente, int idCita) {
        this.idFactura = idFactura;
        this.fechaEmision = fechaEmision;
        this.total = total;
        this.idCliente = idCliente;
        this.idCita = idCita;
    }

    /**
     * Obtiene el ID de la factura.
     *
     * @return ID de la factura
     */
    public int getIdFactura() {
        return idFactura;
    }

    /**
     * Establece el ID de la factura.
     *
     * @param idFactura ID de la factura a establecer
     */
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * Obtiene la fecha de emisión.
     *
     * @return Fecha de emisión
     */
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Establece la fecha de emisión.
     *
     * @param fechaEmision Fecha a establecer
     */
    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Obtiene el monto total.
     *
     * @return Monto total
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el monto total.
     *
     * @param total Monto a establecer
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return ID del cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param idCliente ID del cliente a establecer
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el ID de la cita.
     *
     * @return ID de la cita
     */
    public int getIdCita() {
        return idCita;
    }

    /**
     * Establece el ID de la cita.
     *
     * @param idCita ID de la cita a establecer
     */
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

}
