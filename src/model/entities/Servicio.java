package model.entities;

/**
 * Clase que representa un servicio en el sistema veterinario.
 * Contiene información sobre los servicios ofrecidos.
 *
 * @author Mariana Yagual
 * @version 1.0
 */
public class Servicio {
    private int idServicio;
    private String nombreServicio;
    private String descripcion;
    private double precio;

    /**
     * Constructor por defecto de Servicio.
     */
    public Servicio() {}

    /**
     * Constructor con parámetros básicos para crear un servicio.
     *
     * @param nombreServicio Nombre del servicio
     * @param descripcion    Descripción del servicio
     * @param precio         Precio del servicio
     */
    public Servicio(String nombreServicio, String descripcion, double precio) {
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Constructor completo para crear un servicio.
     *
     * @param idServicio     ID único del servicio
     * @param nombreServicio Nombre del servicio
     * @param descripcion    Descripción del servicio
     * @param precio         Precio del servicio
     */
    public Servicio(int idServicio, String nombreServicio, String descripcion, double precio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Obtiene el ID del servicio.
     *
     * @return ID del servicio
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * Establece el ID del servicio.
     *
     * @param idServicio ID del servicio a establecer
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * Obtiene el nombre del servicio.
     *
     * @return Nombre del servicio
     */
    public String getNombreServicio() {
        return nombreServicio;
    }

    /**
     * Establece el nombre del servicio.
     *
     * @param nombreServicio Nombre a establecer
     */
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    /**
     * Obtiene la descripción del servicio.
     *
     * @return Descripción del servicio
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del servicio.
     *
     * @param descripcion Descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del servicio.
     *
     * @return Precio del servicio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del servicio.
     *
     * @param precio Precio a establecer
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}