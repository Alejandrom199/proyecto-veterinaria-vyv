package model.entities;

/**
 * Clase que representa un cliente en el sistema veterinario.
 * Contiene información personal del cliente.
 *
 * @author Andy Romero
 * @version 1.0
 */
public class Cliente {
    private int idCliente;
    private String nombre;
    private String telefono;

    /**
     * Constructor por defecto de Cliente.
     */
    public Cliente() {
    }

    /**
     * Constructor con parámetros básicos para crear un cliente.
     *
     * @param nombre   Nombre completo del cliente
     * @param telefono Número de teléfono del cliente
     */
    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    /**
     * Constructor completo para crear un cliente.
     *
     * @param idCliente ID único del cliente
     * @param nombre    Nombre completo del cliente
     * @param telefono  Número de teléfono del cliente
     */
    public Cliente(int idCliente, String nombre, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
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
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre Nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el teléfono del cliente.
     *
     * @return Número de teléfono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     *
     * @param telefono Número de teléfono a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Representación en String del objeto Cliente.
     *
     * @return String con los datos del cliente
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
