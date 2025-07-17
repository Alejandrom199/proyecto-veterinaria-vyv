package model.entities;

import java.util.Objects;

/**
 * Clase que representa un usuario del sistema.
 * Contiene información de autenticación y roles.
 *
 * @author Saúl Maldonado
 * @version 1.0
 */
public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String rol;

    /**
     * Constructor por defecto de Usuario.
     */
    public Usuario(){}

    /**
     * Constructor con parámetros para crear un usuario.
     *
     * @param nombreUsuario Nombre de usuario para autenticación
     * @param contrasena    Contraseña del usuario
     * @param rol           Rol del usuario en el sistema
     */
    public Usuario(String nombreUsuario, String contrasena, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return ID del usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param idUsuario ID del usuario a establecer
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre de usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombreUsuario Nombre de usuario a establecer
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena Contraseña a establecer
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return Rol del usuario
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol Rol a establecer
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Compara este usuario con otro objeto para verificar igualdad.
     *
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario &&
                Objects.equals(nombreUsuario, usuario.nombreUsuario) &&
                Objects.equals(contrasena, usuario.contrasena) &&
                Objects.equals(rol, usuario.rol);
    }

    /**
     * Genera el código hash para el usuario.
     *
     * @return Código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombreUsuario, contrasena, rol);
    }

}
