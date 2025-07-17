package model.entities;

/**
 * Clase que representa un medicamento en el sistema veterinario.
 * Contiene información sobre los medicamentos disponibles.
 *
 * @author Jeremy Alvarado
 * @version 1.0
 */
public class Medicamento {
    private int idMedicamento;
    private String nombre;
    private String descripcion;
    private double precio;

    /**
     * Constructor por defecto de Medicamento.
     */
    public Medicamento() {
    }

    /**
     * Constructor con parámetros básicos para crear un medicamento.
     *
     * @param nombre      Nombre del medicamento
     * @param descripcion Descripción del medicamento
     * @param precio      Precio del medicamento
     */
    public Medicamento(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Constructor completo para crear un medicamento.
     *
     * @param idMedicamento ID único del medicamento
     * @param nombre        Nombre del medicamento
     * @param descripcion   Descripción del medicamento
     * @param precio        Precio del medicamento
     */
    public Medicamento(int idMedicamento, String nombre, String descripcion, double precio) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Obtiene el ID del medicamento.
     *
     * @return ID del medicamento
     */
    public int getIdMedicamento() {
        return idMedicamento;
    }

    /**
     * Establece el ID del medicamento.
     *
     * @param idMedicamento ID del medicamento a establecer
     */
    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    /**
     * Obtiene el nombre del medicamento.
     *
     * @return Nombre del medicamento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del medicamento.
     *
     * @param nombre Nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del medicamento.
     *
     * @return Descripción del medicamento
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del medicamento.
     *
     * @param descripcion Descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del medicamento.
     *
     * @return Precio del medicamento
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del medicamento.
     *
     * @param precio Precio a establecer
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
