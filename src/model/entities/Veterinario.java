package model.entities;

/**
 * Clase que representa un veterinario en el sistema.
 * Contiene información profesional del veterinario.
 *
 * @author Lenny Borbor
 * @version 1.0
 */
public class Veterinario {
    private int idVeterinario;
    private String nombre;
    private String especialidad;
    private String telefono;
    private String email;

    /**
     * Constructor por defecto de Veterinario.
     */
    public Veterinario() {}

    /**
     * Constructor con parámetros básicos para crear un veterinario.
     *
     * @param nombre       Nombre completo del veterinario
     * @param especialidad Especialidad del veterinario
     * @param telefono     Teléfono del veterinario
     * @param email        Email del veterinario
     */
    public Veterinario(String nombre, String especialidad, String telefono, String email) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Constructor completo para crear un veterinario.
     *
     * @param idVeterinario ID único del veterinario
     * @param nombre        Nombre completo del veterinario
     * @param especialidad  Especialidad del veterinario
     * @param telefono      Teléfono del veterinario
     * @param email         Email del veterinario
     */
    public Veterinario(int idVeterinario, String nombre, String especialidad, String telefono, String email) {
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Obtiene el ID del veterinario.
     *
     * @return ID del veterinario
     */
    public int getIdVeterinario() {
        return idVeterinario;
    }

    /**
     * Establece el ID del veterinario.
     *
     * @param idVeterinario ID del veterinario a establecer
     */
    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    /**
     * Obtiene el nombre del veterinario.
     *
     * @return Nombre del veterinario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del veterinario.
     *
     * @param nombre Nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la especialidad del veterinario.
     *
     * @return Especialidad del veterinario
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad del veterinario.
     *
     * @param especialidad Especialidad a establecer
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene el teléfono del veterinario.
     *
     * @return Teléfono del veterinario
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del veterinario.
     *
     * @param telefono Teléfono a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el email del veterinario.
     *
     * @return Email del veterinario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del veterinario.
     *
     * @param email Email a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }
}