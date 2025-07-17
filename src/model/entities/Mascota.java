package model.entities;

/**
 * Clase que representa una mascota en el sistema veterinario.
 * Contiene información sobre las mascotas de los clientes.
 *
 * @author Luis Aguirre
 * @version 1.0
 */
public class Mascota {
    private int idMascota;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String sexo;
    private int idCliente; // FK
    private Cliente cliente;

    /**
     * Constructor por defecto de Mascota.
     */
    public Mascota() {}

    /**
     * Constructor completo para crear una mascota.
     *
     * @param idMascota ID único de la mascota
     * @param nombre    Nombre de la mascota
     * @param especie   Especie de la mascota
     * @param raza      Raza de la mascota
     * @param edad      Edad de la mascota
     * @param sexo      Sexo de la mascota
     * @param idCliente ID del cliente dueño
     */
    public Mascota(int idMascota, String nombre, String especie, String raza, int edad, String sexo, int idCliente) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.sexo = sexo;
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el ID de la mascota.
     *
     * @return ID de la mascota
     */
    public int getIdMascota() {
        return idMascota;
    }

    /**
     * Establece el ID de la mascota.
     *
     * @param idMascota ID de la mascota a establecer
     */
    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    /**
     * Obtiene el nombre de la mascota.
     *
     * @return Nombre de la mascota
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la mascota.
     *
     * @param nombre Nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la especie de la mascota.
     *
     * @return Especie de la mascota
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Establece la especie de la mascota.
     *
     * @param especie Especie a establecer
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Obtiene la raza de la mascota.
     *
     * @return Raza de la mascota
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Establece la raza de la mascota.
     *
     * @param raza Raza a establecer
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Obtiene la edad de la mascota.
     *
     * @return Edad de la mascota
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad de la mascota.
     *
     * @param edad Edad a establecer
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el sexo de la mascota.
     *
     * @return Sexo de la mascota
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo de la mascota.
     *
     * @param sexo Sexo a establecer
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene el ID del cliente dueño.
     *
     * @return ID del cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el ID del cliente dueño.
     *
     * @param idCliente ID del cliente a establecer
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el objeto Cliente asociado.
     *
     * @return Objeto Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el objeto Cliente asociado.
     *
     * @param cliente Objeto Cliente a establecer
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
