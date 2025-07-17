package model.entities;

import java.time.LocalDateTime;

/**
 * Clase que representa una cita en el sistema veterinario.
 * Contiene información sobre fecha, motivo, estado y participantes.
 *
 * @author Saúl Maldonado
 * @version 1.0
 */
public class Cita {
    private int idCita;
    private LocalDateTime fechaHora;
    private String motivo;
    private String estado;
    private int idMascota;     // FK
    private int idVeterinario; // FK

    /**
     * Constructor por defecto de Cita.
     */
    public Cita() {}

    /**
     * Constructor con parámetros básicos para crear una cita.
     *
     * @param fechaHora     Fecha y hora de la cita
     * @param motivo        Motivo de la cita
     * @param estado        Estado actual de la cita
     * @param idMascota     ID de la mascota asociada
     * @param idVeterinario ID del veterinario asignado
     */
    public Cita(LocalDateTime fechaHora, String motivo, String estado, int idMascota, int idVeterinario) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
    }

    /**
     * Constructor completo para crear una cita.
     *
     * @param idCita        ID único de la cita
     * @param fechaHora     Fecha y hora de la cita
     * @param motivo        Motivo de la cita
     * @param estado        Estado actual de la cita
     * @param idMascota     ID de la mascota asociada
     * @param idVeterinario ID del veterinario asignado
     */
    public Cita(int idCita, LocalDateTime fechaHora, String motivo, String estado, int idMascota, int idVeterinario) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
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

    /**
     * Obtiene la fecha y hora de la cita.
     *
     * @return Fecha y hora de la cita
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la cita.
     *
     * @param fechaHora Fecha y hora a establecer
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el motivo de la cita.
     *
     * @return Motivo de la cita
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo de la cita.
     *
     * @param motivo Motivo a establecer
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Obtiene el estado de la cita.
     *
     * @return Estado de la cita
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la cita.
     *
     * @param estado Estado a establecer
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el ID de la mascota asociada.
     *
     * @return ID de la mascota
     */
    public int getIdMascota() {
        return idMascota;
    }

    /**
     * Establece el ID de la mascota asociada.
     *
     * @param idMascota ID de la mascota a establecer
     */
    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    /**
     * Obtiene el ID del veterinario asignado.
     *
     * @return ID del veterinario
     */
    public int getIdVeterinario() {
        return idVeterinario;
    }

    /**
     * Establece el ID del veterinario asignado.
     *
     * @param idVeterinario ID del veterinario a establecer
     */
    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }
}
