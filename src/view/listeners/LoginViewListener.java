package view.listeners;

import controller.UsuarioController;
import model.dto.LoginDTO;
import model.dto.UsuarioDTO;
import model.entities.Usuario;
import utils.AppFactory;
import utils.SessionManager;
import view.views.IndexView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener para manejar los eventos de la vista de login.
 * Implementa la lógica de autenticación y redirección a la vista principal.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class LoginViewListener implements ActionListener {

    private LoginDTO loginDTO;
    private final UsuarioController usuarioController;

    /**
     * Constructor que inicializa el listener con los componentes necesarios.
     *
     * @param loginDTO Objeto que contiene los componentes de la vista de login
     */
    public LoginViewListener(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
        usuarioController = AppFactory.getUsuarioController();
    }

    /**
     * Método que se ejecuta al hacer clic en el botón de login.
     * Maneja la autenticación del usuario y la navegación a la vista principal.
     *
     * @param e Evento de acción
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String nombreUsuario = loginDTO.txtUsername().getText();
        String contrasena = new String(loginDTO.txtPassword().getPassword());
        boolean recordarSesion = loginDTO.chkRecordar().isSelected();

        // Autenticar con el AuthController
        if (loginDTO.authController().autenticarUsuario(nombreUsuario, contrasena)) {

            JOptionPane.showMessageDialog(null, "Bienvenido, " + nombreUsuario);
            // Aquí podrías abrir otra vista, por ejemplo, el menú principal

            if(recordarSesion){
                SessionManager.guardarCredenciales(nombreUsuario, contrasena);
            }

            loginDTO.txtUsername().getTopLevelAncestor().setVisible(false);

            Usuario usuario = usuarioController.buscarUsuarioPorNombreUsuario(loginDTO.txtUsername().getText());
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNombreUsuario(), usuario.getRol());

            IndexView  indexView = new IndexView(usuarioDTO);
            indexView.setVisible(true);

        }
        else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
